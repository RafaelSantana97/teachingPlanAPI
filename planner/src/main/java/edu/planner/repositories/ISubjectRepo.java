package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.planner.models.Subject;

public interface ISubjectRepo extends PagingAndSortingRepository<Subject, Integer> {

	public Page<Subject> findByNameContaining(Pageable page, String description);

	public Iterable<Subject> findByCoursesIdIs(Integer coursesId);
}