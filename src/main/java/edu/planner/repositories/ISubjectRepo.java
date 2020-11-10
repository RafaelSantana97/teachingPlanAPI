package edu.planner.repositories;

import edu.planner.models.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISubjectRepo extends PagingAndSortingRepository<Subject, Long> {

	Page<Subject> findByNameContaining(Pageable page, String description);

	Iterable<Subject> findByCoursesIdIs(Long coursesId);
}