package edu.planner.security.permission;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPermissionRepo extends PagingAndSortingRepository<Permission, Long> {

}