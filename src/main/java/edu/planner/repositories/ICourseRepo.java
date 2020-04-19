package edu.planner.repositories;

import edu.planner.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICourseRepo extends PagingAndSortingRepository<Course, Long> {

    Page<Course> findByNameContaining(Pageable page, String description);
}