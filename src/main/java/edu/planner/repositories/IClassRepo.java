package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.planner.models.Class;

public interface IClassRepo extends PagingAndSortingRepository<Class, Long> {

    Page<Class> findBySubjectNameContaining(Pageable page, String description);
}