package edu.planner.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import edu.planner.enums.Profile;
import edu.planner.models.User;

public interface IUserRepo extends PagingAndSortingRepository<User, Long> {

    Page<User> findByNameContaining(Pageable page, String description);

    Page<User> findByProfilesInAndNameContaining(Pageable page, Short profile, String description);

    Page<User> findByProfilesIs(Pageable page, Short profile);

    @Transactional(readOnly = true)
    User findByEmail(String email);

    @Transactional(readOnly = true)
    Boolean existsByProfilesIn(Short profile);

    default Boolean existsSomeAdminUser() {
        return existsByProfilesIn(Profile.ADMIN.getId());
    }

    Page<User> findDistinctByRequiredProfilesNotNull(Pageable page);
}