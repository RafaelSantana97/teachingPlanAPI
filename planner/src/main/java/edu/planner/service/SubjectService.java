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
import edu.planner.models.Subject;
import edu.planner.repositories.ISubjectRepo;

@Service
public class SubjectService implements IService<Subject> {

	@Autowired
	ISubjectRepo iSubjectRepo;

	@Autowired
	UserService userService;

	@Override
	public Subject insert(Subject subject) {
		Subject subjectIncluida = null;
		try {
			subject.setResponsible(userService.findOne(subject.getResponsible().getId()));

			if (!subject.getResponsible().getIsTeacher()) {
				throw new BusinessException(ErrorCode.SUBJECT_NEED_A_TEACHER);
			}

			subjectIncluida = iSubjectRepo.save(subject);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SAVE, e);
		}
		return subjectIncluida;
	}

	@Override
	public Subject update(Subject subject) {
		Subject subjectAlterada = null;
		try {
			subject.setResponsible(userService.findOne(subject.getResponsible().getId()));

			if (!subject.getResponsible().getIsTeacher()) {
				throw new BusinessException(ErrorCode.SUBJECT_NEED_A_TEACHER);
			}

			subjectAlterada = iSubjectRepo.save(subject);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_UPDATE, e);
		}
		return subjectAlterada;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iSubjectRepo.delete(findOne(id));
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.SUBJECT_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_DELETE, e);
		}
		return retorno;
	}

	public Page<Subject> findPageableAndFiltered(int page, int count, String description) {
		Page<Subject> subject = null;
		try {
			subject = iSubjectRepo.findByNameContaining(PageRequest.of(page, count), description);

		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
		}

		return subject;
	}

	public Page<Subject> findPageable(int page, int count) {
		Page<Subject> subject = null;
		try {
			subject = iSubjectRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
		}

		return subject;
	}

	public Iterable<Subject> findAll() {
		Iterable<Subject> subject = null;
		try {
			subject = iSubjectRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
		}

		return subject;
	}

	public Subject findOne(int id) {
		Optional<Subject> subject = null;
		try {
			subject = iSubjectRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
		}

		return subject.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.SUBJECT_NOT_FOUND));
	}
}