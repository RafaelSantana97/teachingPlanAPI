package edu.planner.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.Privilegio;
import edu.planner.repositories.IPrivilegioRepo;

@Service
public class PrivilegioService implements IService<Privilegio> {

	@Autowired
	IPrivilegioRepo iPrivilegioRepo;

	@Override
	public Privilegio insert(Privilegio privilegio) {
		Privilegio privilegioIncluido = null;
		try {
			privilegioIncluido = iPrivilegioRepo.save(privilegio);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_SAVE, e);
		}
		return privilegioIncluido;
	}

	@Override
	public Privilegio update(Privilegio privilegio) {
		Privilegio privilegioAlterado = null;
		try {
			privilegioAlterado = iPrivilegioRepo.save(privilegio);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_UPDATE, e);
		}
		return privilegioAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iPrivilegioRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_DELETE, e);
		}
		return retorno;
	}

	public Page<Privilegio> findPageableAndFiltered(int page, int count, String description) {
		Page<Privilegio> privilegio = null;
		try {
			privilegio = iPrivilegioRepo.findByNameContaining(PageRequest.of(page, count), description);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_SEARCH, e);
		}

		return privilegio;
	}

	public Iterable<Privilegio> findAll() {
		Iterable<Privilegio> privilegio = null;
		try {
			privilegio = iPrivilegioRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_SEARCH, e);
		}

		return privilegio;
	}

	public Privilegio findOne(int id) {
		Optional<Privilegio> privilegio = null;
		try {
			privilegio = iPrivilegioRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.PRIVILEGIO_SEARCH, e);
		}

		return privilegio.orElseThrow(() -> new BusinessException(ErrorCode.PRIVILEGIO_NOT_FOUND));
	}
}