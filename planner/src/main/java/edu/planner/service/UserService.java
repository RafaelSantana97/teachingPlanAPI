package edu.planner.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.planner.enums.Profile;
import edu.planner.exception.AuthorizationException;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.User;
import edu.planner.repositories.IUserRepo;
import edu.planner.security.UserSS;

@Service
public class UserService implements IService<User, User> {

	@Autowired
	IUserRepo iUserRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User insert(User user) {
		User userIncluded = null;
		try {
			user.setHashKey(bCryptPasswordEncoder.encode(user.getHashKey()));
			userIncluded = iUserRepo.save(user);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SAVE, e);
		}
		return userIncluded;
	}

	@Override
	public User update(User user) {
		User userAltered = null;
		try {
			userAltered = iUserRepo.save(user);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_UPDATE, e);
		}
		return userAltered;
	}

	@Override
	public Boolean delete(Long id) {
		Boolean result = false;

		try {
			iUserRepo.deleteById(id);
			result = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.USER_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_DELETE, e);
		}
		return result;
	}

	public Page<User> findPageableAndFiltered(int page, int count, String description) {
		Page<User> user = null;
		try {
			user = iUserRepo.findByNameContaining(PageRequest.of(page, count), description);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user;
	}

	public Page<User> findPageable(int page, int count) {
		Page<User> user = null;
		try {
			user = iUserRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user;
	}

	public Page<User> findPageableAndFilteredProfile(int page, int count, Short profile, String description) {
		Page<User> user = null;
		try {
			user = iUserRepo.findByProfilesInAndNameContaining(PageRequest.of(page, count), profile, description);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user;
	}

	public Page<User> findPageableByProfile(int page, int count, Short profile) {
		Page<User> user = null;
		try {
			user = iUserRepo.findByProfilesIs(PageRequest.of(page, count), profile);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user;
	}

	public Iterable<User> findAll() {
		Iterable<User> user = null;
		try {
			user = iUserRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user;
	}

	public static UserSS authenticated() {
		try {
		return(UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
			}
		}
	
	public User findOne(Long id) {
		
		UserSS userSS = UserService.authenticated(); 
		if(userSS == null || userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("Access denied");
		}
		
		Optional<User> user = null;
		try {
			user = iUserRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
	}
}