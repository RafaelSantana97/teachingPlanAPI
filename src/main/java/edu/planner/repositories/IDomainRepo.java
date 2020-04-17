package edu.planner.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import edu.planner.models.Domain;

public interface IDomainRepo extends PagingAndSortingRepository<Domain, Integer> {

}