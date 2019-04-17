package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.planner.models.UserType;

public interface IUserTypeRepo extends PagingAndSortingRepository<UserType, Integer> {

	public Page<UserType> findByNameContaining(Pageable page, String description);
}