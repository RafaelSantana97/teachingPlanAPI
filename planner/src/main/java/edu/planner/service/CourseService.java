package edu.planner.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.Course;
import edu.planner.repositories.ICourseRepo;

@Service
public class CourseService implements IService<Course> {

	@Autowired
	ICourseRepo iCourseRepo;

	@Override
	public Course insert(Course course) {
		Course courseIncluido = null;
		try {
			courseIncluido = iCourseRepo.save(course);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SAVE, e);
		}
		return courseIncluido;
	}

	@Override
	public Course update(Course course) {
		Course courseAlterado = null;
		try {
			courseAlterado = iCourseRepo.save(course);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_UPDATE, e);
		}
		return courseAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iCourseRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.COURSE_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_DELETE, e);
		}
		return retorno;
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

	public Course findOne(int id) {
		Optional<Course> course = null;
		try {
			course = iCourseRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
		}

		return course.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));
	}
}