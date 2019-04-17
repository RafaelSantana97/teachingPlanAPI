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
public class ClassService implements IService<Class> {

	@Autowired
	IClassRepo iClasseRepo;

	@Autowired
	SubjectService subjectService;

	@Autowired
	UserService userService;

	@Override
	public Class insert(Class _class) {
		Class _classeIncluida = null;
		try {
			_class.setTeacher(userService.findOne(_class.getTeacher().getId()));

			if (!_class.getTeacher().getIsTeacher()) {
				throw new BusinessException(ErrorCode.CLASS_NEED_A_TEACHER);
			}

			_class.setSubject(subjectService.findOne(_class.getSubject().getId()));

			_classeIncluida = iClasseRepo.save(_class);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SAVE, e);
		}
		return _classeIncluida;
	}

	@Override
	public Class update(Class _class) {
		Class _classeAlterada = null;
		try {
			_class.setTeacher(userService.findOne(_class.getTeacher().getId()));

			if (!_class.getTeacher().getIsTeacher()) {
				throw new BusinessException(ErrorCode.CLASS_NEED_A_TEACHER);
			}

			_classeAlterada = iClasseRepo.save(_class);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_UPDATE, e);
		}
		return _classeAlterada;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iClasseRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.CLASS_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_DELETE, e);
		}
		return retorno;
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

	public Class findOne(int id) {
		Optional<Class> _class = null;
		try {
			_class = iClasseRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CLASS_SEARCH, e);
		}

		return _class.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.CLASS_NOT_FOUND));
	}
}