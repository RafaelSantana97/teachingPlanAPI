package edu.planner.repositories;

import edu.planner.models.Domain;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDomainRepo extends PagingAndSortingRepository<Domain, Integer> {

}