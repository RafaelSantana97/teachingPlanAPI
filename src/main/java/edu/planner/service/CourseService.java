package edu.planner.service;

import edu.planner.dto.CourseDTO;
import edu.planner.dto.SubjectDTO;
import edu.planner.dto.mapper.CourseMapper;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.Course;
import edu.planner.repositories.ICourseRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService implements IService<Course, CourseDTO> {

    private final ICourseRepo iCourseRepo;
    private final SubjectService subjectService;

    @Override
    @Transactional
    public Course insert(CourseDTO course) {
        try {
            Course courseIncluded = CourseMapper.from(course);
            return iCourseRepo.save(courseIncluded);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SAVE, e);
        }
    }

    @Override
    @Transactional
    public Course update(CourseDTO course) {
        try {
            Course courseAltered = CourseMapper.from(course);
            return iCourseRepo.save(courseAltered);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_UPDATE, e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            iCourseRepo.deleteById(id);
        } catch (ConstraintViolationException e) {
            throw new BusinessException(ErrorCode.COURSE_DELETE_VIOLATION, e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_DELETE, e);
        }
    }

    public Page<Course> findPageableAndFiltered(int page, int count, String description) {
        try {
            return iCourseRepo.findByNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }
    }

    public Page<Course> findPageable(int page, int count) {
        try {
            return iCourseRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }
    }

    public Iterable<Course> findAll() {
        try {
            return iCourseRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }
    }

    public CourseDTO findOne(Long id) {
        try {
            Optional<Course> course = iCourseRepo.findById(id);

            Course courseToDTO = course.orElseThrow(() -> new BusinessException(ErrorCode.COURSE_NOT_FOUND));
            ArrayList<SubjectDTO> subjects = (ArrayList<SubjectDTO>) subjectService.findByCourse(id);

            return CourseMapper.to(courseToDTO, subjects);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.COURSE_SEARCH, e);
        }
    }
}