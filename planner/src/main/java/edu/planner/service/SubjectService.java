package edu.planner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.dto.SubjectDTO;
import edu.planner.dto.SubjectInsertDTO;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.ObjectNotFoundException;
import edu.planner.interfaces.IService;
import edu.planner.models.Subject;
import edu.planner.repositories.ISubjectRepo;

@Service
public class SubjectService implements IService<Subject, SubjectInsertDTO> {

	@Autowired
	ISubjectRepo iSubjectRepo;

	@Autowired
	UserService userService;

	public Subject insert(SubjectInsertDTO subject) {
		Subject subjectIncluded = null;
		try {
			subjectIncluded = iSubjectRepo.save(SubjectInsertDTO.fromDTO(subject));
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SAVE, e);
		}
		return subjectIncluded;
	}

	public Subject update(SubjectInsertDTO subject) {
		Subject subjectAltered = null;
		try {
			subjectAltered = iSubjectRepo.save(SubjectInsertDTO.fromDTO(subject));
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_UPDATE, e);
		}
		return subjectAltered;
	}

	public Boolean delete(Long id) {
		Boolean result = false;

		try {
			iSubjectRepo.delete(findOne(id));
			result = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.SUBJECT_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_DELETE, e);
		}
		return result;
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

	public Iterable<SubjectDTO> findByCourse(Long courseId) {
		List<Subject> subjects = null;
		List<Subject> subjectsChecked = new ArrayList<Subject>();
		List<SubjectDTO> subjectsDTO = new ArrayList<SubjectDTO>();

		try {
			subjects = (ArrayList<Subject>) iSubjectRepo.findAll();

			if (courseId != null && courseId > 0) {
				subjectsChecked = (ArrayList<Subject>) iSubjectRepo.findByCoursesIdIs(courseId);

				subjectsChecked.forEach(sub -> subjectsDTO.add(SubjectDTO.toDTO(sub, true)));

				subjects.removeAll(subjectsChecked);
			}

			subjects.forEach(sub -> subjectsDTO.add(SubjectDTO.toDTO(sub, false)));

		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
		}

		return subjectsDTO;
	}

	public Subject findOne(Long id) {
		Optional<Subject> subject = null;
		try {
			subject = iSubjectRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
		}

		return subject.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.SUBJECT_NOT_FOUND));
	}
}