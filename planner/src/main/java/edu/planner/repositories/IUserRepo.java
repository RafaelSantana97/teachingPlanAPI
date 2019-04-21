package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.planner.models.User;

public interface IUserRepo extends PagingAndSortingRepository<User, Long> {

	public Page<User> findByNameContaining(Pageable page, String description);

	public Page<User> findByProfilesInAndNameContaining(Pageable page, Integer profile, String description);
	
	public Page<User> findByProfilesIs(Pageable page, Integer profile);

	@Transactional(readOnly=true)
	public User findByEmail(String email);
}