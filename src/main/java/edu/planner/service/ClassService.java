package edu.planner.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.ObjectNotFoundException;
import edu.planner.interfaces.IService;
import edu.planner.models.Class;
import edu.planner.repositories.IClassRepo;

@Service
@RequiredArgsConstructor
public class ClassService implements IService<Class, Class> {

    private final IClassRepo iClassRepo;

    @Override
    public Class insert(Class clazz) {
        Class classIncluded;
        try {
            classIncluded = iClassRepo.save(clazz);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SAVE, e);
        }
        return classIncluded;
    }

    @Override
    public Class update(Class clazz) {
        Class classAltered;
        try {
            classAltered = iClassRepo.save(clazz);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_UPDATE, e);
        }
        return classAltered;
    }

    @Override
    public Boolean delete(Long id) {
        Boolean result = false;

        try {
            iClassRepo.deleteById(id);
            result = true;
        } catch (ConstraintViolationException e) {
            throw new BusinessException(ErrorCode.CLASS_DELETE_VIOLATION, e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_DELETE, e);
        }
        return result;
    }

    public Page<Class> findPageableAndFiltered(int page, int count, String description) {
        Page<Class> clazz;
        try {
            clazz = iClassRepo.findBySubjectNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }

        return clazz;
    }

    public Page<Class> findPageable(int page, int count) {
        Page<Class> clazz;
        try {
            clazz = iClassRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }

        return clazz;
    }

    public Iterable<Class> findAll() {
        Iterable<Class> clazz;
        try {
            clazz = iClassRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }

        return clazz;
    }

    public Class findOne(Long id) {
        Optional<Class> clazz;
        try {
            clazz = iClassRepo.findById(id);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }

        return clazz.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.CLASS_NOT_FOUND));
    }
}