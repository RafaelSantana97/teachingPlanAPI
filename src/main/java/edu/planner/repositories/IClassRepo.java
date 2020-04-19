package edu.planner.repositories;

import edu.planner.models.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClassRepo extends PagingAndSortingRepository<Class, Long> {

    Page<Class> findBySubjectNameContaining(Pageable page, String description);
}