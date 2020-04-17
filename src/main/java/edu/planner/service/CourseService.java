package edu.planner.service;

import java.util.ArrayList;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
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
@RequiredArgsConstructor
public class CourseService implements IService<Course, CourseDTO> {

    private final ICourseRepo iCourseRepo;
    private final SubjectService subjectService;

    public Course insert(CourseDTO course) {
        Course courseIncluded;
        try {
            courseIncluded = CourseDTO.fromDTO(course);
            courseIncluded = iCourseRepo.save(courseIncluded);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SAVE, e);
        }
        return courseIncluded;
    }

    public Course update(CourseDTO course) {
        Course courseAltered;
        try {
            courseAltered = CourseDTO.fromDTO(course);
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
        Page<Course> course;
        try {
            course = iCourseRepo.findByNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }

        return course;
    }

    public Page<Course> findPageable(int page, int count) {
        Page<Course> course;
        try {
            course = iCourseRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }

        return course;
    }

    public Iterable<Course> findAll() {
        Iterable<Course> course;
        try {
            course = iCourseRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }

        return course;
    }

    public CourseDTO findOne(Long id) {
        CourseDTO courseDTO;
        try {
            Optional<Course> course = iCourseRepo.findById(id);

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