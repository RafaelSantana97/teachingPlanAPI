package edu.planner.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.TipoUsuario;
import edu.planner.repositories.ITipoUsuarioRepo;

@Service
public class TipoUsuarioService implements IService<TipoUsuario> {

	@Autowired
	ITipoUsuarioRepo iTipoUsuarioRepo;

	@Override
	public TipoUsuario insert(TipoUsuario tipoUsuario) {
		TipoUsuario tipoUsuarioIncluido = null;
		try {
			tipoUsuarioIncluido = iTipoUsuarioRepo.save(tipoUsuario);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TIPO_USUARIO_SAVE, e);
		}
		return tipoUsuarioIncluido;
	}

	@Override
	public TipoUsuario update(TipoUsuario tipoUsuario) {
		TipoUsuario tipoUsuarioAlterado = null;
		try {
			tipoUsuarioAlterado = iTipoUsuarioRepo.save(tipoUsuario);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TIPO_USUARIO_UPDATE, e);
		}
		return tipoUsuarioAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iTipoUsuarioRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.TIPO_USUARIO_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TIPO_USUARIO_DELETE, e);
		}
		return retorno;
	}

	public Page<TipoUsuario> findPageableAndFiltered(int page, int count, String descricao) {
		Page<TipoUsuario> tipoUsuario = null;
		try {
			tipoUsuario = iTipoUsuarioRepo.findByNomeContaining(PageRequest.of(page, count), descricao);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TIPO_USUARIO_SEARCH, e);
		}

		return tipoUsuario;
	}
}