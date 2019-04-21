package edu.planner.service;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.dto.CourseDTO;
import edu.planner.dto.SubjectDTO;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.Course;
import edu.planner.repositories.ICourseRepo;

@Service
public class CourseService implements IService<Course, CourseDTO> {

	@Autowired
	ICourseRepo iCourseRepo;

	@Autowired
	SubjectService subjectService;

	public Course insert(CourseDTO course) {
		Course courseIncluded = null;
		try {
			courseIncluded = new Course(course.getId(), course.getName());
			courseIncluded.setCoordinators(course.getCoordinators());

			for (SubjectDTO sub : course.getSubjects()) {
				if (sub.isChecked()) {
					courseIncluded.getSubjects().add(SubjectDTO.fromDTO(sub));
				}
			}

			courseIncluded = iCourseRepo.save(courseIncluded);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SAVE, e);
		}
		return courseIncluded;
	}

	public Course update(CourseDTO course) {
		Course courseAltered = null;
		try {
			courseAltered = new Course(course.getId(), course.getName());
			courseAltered.setCoordinators(course.getCoordinators());

			for (SubjectDTO sub : course.getSubjects()) {
				if (sub.isChecked()) {
					courseAltered.getSubjects().add(SubjectDTO.fromDTO(sub));
				}
			}

			courseAltered = iCourseRepo.save(courseAltered);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_UPDATE, e);
		}
		return courseAltered;
	}

	public Boolean delete(Long id) {
		Boolean result = false;

		try {
			iCourseRepo.deleteById(id);
			result = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.COURSE_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_DELETE, e);
		}
		return result;
	}

	public Page<Course> findPageableAndFiltered(int page, int count, String description) {
		Page<Course> course = null;
		try {
			course = iCourseRepo.findByNameContaining(PageRequest.of(page, count), description);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
		}

		return course;
	}

	public Page<Course> findPageable(int page, int count) {
		Page<Course> course = null;
		try {
			course = iCourseRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
		}

		return course;
	}

	public Iterable<Course> findAll() {
		Iterable<Course> course = null;
		try {
			course = iCourseRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
		}

		return course;
	}

	public CourseDTO findOne(Long id) {
		Optional<Course> course = null;
		CourseDTO courseDTO = null;
		try {
			course = iCourseRepo.findById(id);

			Course courseToDTO = course.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));
			ArrayList<SubjectDTO> subjects = (ArrayList<SubjectDTO>) subjectService.findByCourse(id);

			courseDTO = CourseDTO.toDTO(courseToDTO, subjects);

		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
		}

		return courseDTO;
	}
}