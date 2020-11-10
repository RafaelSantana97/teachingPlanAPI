package edu.planner.service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.ObjectNotFoundException;
import edu.planner.interfaces.IService;
import edu.planner.models.Class;
import edu.planner.repositories.IClassRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService implements IService<Class, Class> {

    private final IClassRepo iClassRepo;

    @Override
    @Transactional
    public Class insert(Class clazz) {
        try {
            return iClassRepo.save(clazz);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SAVE, e);
        }
    }

    @Override
    @Transactional
    public Class update(Class clazz) {
        try {
            return iClassRepo.save(clazz);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_UPDATE, e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            iClassRepo.deleteById(id);
        } catch (ConstraintViolationException e) {
            throw new BusinessException(ErrorCode.CLASS_DELETE_VIOLATION, e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_DELETE, e);
        }
    }

    public Page<Class> findPageableAndFiltered(int page, int count, String description) {
        try {
            return iClassRepo.findBySubjectNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }
    }

    public Page<Class> findPageable(int page, int count) {
        try {
            return iClassRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }
    }

    public Iterable<Class> findAll() {
        try {
            return iClassRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
        }
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