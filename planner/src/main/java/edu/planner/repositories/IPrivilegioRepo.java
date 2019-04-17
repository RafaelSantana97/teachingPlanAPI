package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.planner.models.Privilegio;

public interface IPrivilegioRepo extends PagingAndSortingRepository<Privilegio, Integer> {

	public Page<Privilegio> findByNameContaining(Pageable page, String description);
}