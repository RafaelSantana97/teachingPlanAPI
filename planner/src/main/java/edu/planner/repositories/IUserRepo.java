package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.planner.enums.Profile;
import edu.planner.models.User;

public interface IUserRepo extends PagingAndSortingRepository<User, Long> {

	public Page<User> findByNameContaining(Pageable page, String description);

	public Page<User> findByProfilesInAndNameContaining(Pageable page, Short profile, String description);

	public Page<User> findByProfilesIs(Pageable page, Short profile);

	@Transactional(readOnly = true)
	public User findByEmail(String email);

	@Transactional(readOnly = true)
	public Boolean existsByProfilesIn(Short profile);

	public default Boolean existsSomeAdminUser() {
		return existsByProfilesIn(Profile.ADMIN.getId());
	}
}