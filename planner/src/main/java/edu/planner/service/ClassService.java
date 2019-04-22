package edu.planner.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClassService implements IService<Class, Class> {

	@Autowired
	IClassRepo iClasseRepo;

	@Autowired
	SubjectService subjectService;

	@Autowired
	UserService userService;

	@Override
	public Class insert(Class _class) {
		Class _classeIncluded = null;
		try {
			_classeIncluded = iClasseRepo.save(_class);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SAVE, e);
		}
		return _classeIncluded;
	}

	@Override
	public Class update(Class _class) {
		Class _classeAltered = null;
		try {
			_classeAltered = iClasseRepo.save(_class);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_UPDATE, e);
		}
		return _classeAltered;
	}

	@Override
	public Boolean delete(Long id) {
		Boolean result = false;

		try {
			iClasseRepo.deleteById(id);
			result = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.CLASS_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_DELETE, e);
		}
		return result;
	}

	public Page<Class> findPageableAndFiltered(int page, int count, String description) {
		Page<Class> _class = null;
		try {
			_class = iClasseRepo.findBySubjectNameContaining(PageRequest.of(page, count), description);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
		}

		return _class;
	}

	public Page<Class> findPageable(int page, int count) {
		Page<Class> _class = null;
		try {
			_class = iClasseRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
		}

		return _class;
	}

	public Iterable<Class> findAll() {
		Iterable<Class> _class = null;
		try {
			_class = iClasseRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
		}

		return _class;
	}

	public Class findOne(Long id) {
		Optional<Class> _class = null;
		try {
			_class = iClasseRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
		}

		return _class.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.CLASS_NOT_FOUND));
	}
}