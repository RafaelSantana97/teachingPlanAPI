package edu.planner.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.User;
import edu.planner.repositories.IUserRepo;

@Service
public class UserService implements IService<User> {

	@Autowired
	IUserRepo iUserRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User insert(User user) {
		User userIncluido = null;
		try {
			user.setHashKey(bCryptPasswordEncoder.encode(user.getHashKey()));
			userIncluido = iUserRepo.save(user);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SAVE, e);
		}
		return userIncluido;
	}

	@Override
	public User update(User user) {
		User userAlterado = null;
		try {
			userAlterado = iUserRepo.save(user);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_UPDATE, e);
		}
		return userAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iUserRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.USER_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_DELETE, e);
		}
		return retorno;
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

	public Iterable<User> findAll() {
		Iterable<User> user = null;
		try {
			user = iUserRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user;
	}

	public User findOne(int id) {
		Optional<User> user = null;
		try {
			user = iUserRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_SEARCH, e);
		}

		return user.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
	}
}