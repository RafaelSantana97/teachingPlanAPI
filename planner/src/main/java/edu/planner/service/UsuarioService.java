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
import edu.planner.models.Usuario;
import edu.planner.repositories.IUsuarioRepo;

@Service
public class UsuarioService implements IService<Usuario> {

	@Autowired
	IUsuarioRepo iUsuarioRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario insert(Usuario usuario) {
		Usuario usuarioIncluido = null;
		try {
			usuario.setHashKey(bCryptPasswordEncoder.encode(usuario.getHashKey()));
			usuarioIncluido = iUsuarioRepo.save(usuario);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_SAVE, e);
		}
		return usuarioIncluido;
	}

	@Override
	public Usuario update(Usuario usuario) {
		Usuario usuarioAlterado = null;
		try {
			usuarioAlterado = iUsuarioRepo.save(usuario);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_UPDATE, e);
		}
		return usuarioAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iUsuarioRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.USUARIO_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_DELETE, e);
		}
		return retorno;
	}

	public Page<Usuario> findPageableAndFiltered(int page, int count, String descricao) {
		Page<Usuario> usuario = null;
		try {
			usuario = iUsuarioRepo.findByNomeContaining(PageRequest.of(page, count), descricao);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_SEARCH, e);
		}

		return usuario;
	}

	public Page<Usuario> findPageable(int page, int count) {
		Page<Usuario> usuario = null;
		try {
			usuario = iUsuarioRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_SEARCH, e);
		}

		return usuario;
	}

	public Iterable<Usuario> findAll() {
		Iterable<Usuario> usuario = null;
		try {
			usuario = iUsuarioRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_SEARCH, e);
		}

		return usuario;
	}

	public Usuario findOne(int id) {
		Optional<Usuario> usuario = null;
		try {
			usuario = iUsuarioRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.USUARIO_SEARCH, e);
		}

		return usuario.orElseThrow(() -> new BusinessException(ErrorCode.USUARIO_NOT_FOUND));
	}
}