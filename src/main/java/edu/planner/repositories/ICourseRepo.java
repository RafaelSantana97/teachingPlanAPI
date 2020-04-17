package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.planner.models.Course;

public interface ICourseRepo extends PagingAndSortingRepository<Course, Long> {

    Page<Course> findByNameContaining(Pageable page, String description);
}