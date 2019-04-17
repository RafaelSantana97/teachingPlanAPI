package edu.planner.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.UserType;
import edu.planner.repositories.IUserTypeRepo;

@Service
public class UserTypeService implements IService<UserType> {

	@Autowired
	IUserTypeRepo iUserTypeRepo;

	@Override
	public UserType insert(UserType userType) {
		UserType userTypeIncluido = null;
		try {
			userTypeIncluido = iUserTypeRepo.save(userType);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_TYPE_SAVE, e);
		}
		return userTypeIncluido;
	}

	@Override
	public UserType update(UserType userType) {
		UserType userTypeAlterado = null;
		try {
			userTypeAlterado = iUserTypeRepo.save(userType);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_TYPE_UPDATE, e);
		}
		return userTypeAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iUserTypeRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.USER_TYPE_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_TYPE_DELETE, e);
		}
		return retorno;
	}

	public Page<UserType> findPageableAndFiltered(int page, int count, String description) {
		Page<UserType> userType = null;
		try {
			userType = iUserTypeRepo.findByNameContaining(PageRequest.of(page, count), description);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USER_TYPE_SEARCH, e);
		}

		return userType;
	}
}