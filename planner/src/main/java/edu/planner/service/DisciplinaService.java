package edu.planner.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.ObjectNotFoundException;
import edu.planner.interfaces.IService;
import edu.planner.models.Disciplina;
import edu.planner.repositories.IDisciplinaRepo;

@Service
public class DisciplinaService implements IService<Disciplina> {

	@Autowired
	IDisciplinaRepo iDisciplinaRepo;

	@Autowired
	UsuarioService usuarioService;

	@Override
	public Disciplina insert(Disciplina disciplina) {
		Disciplina disciplinaIncluida = null;
		try {
			disciplina.setResponsavel(usuarioService.findOne(disciplina.getResponsavel().getId()));

			if (!disciplina.getResponsavel().getIsProfessor()) {
				throw new BusinessException(ErrorCode.DISCIPLINA_NEED_A_PROFESSOR);
			}

			disciplinaIncluida = iDisciplinaRepo.save(disciplina);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_SAVE, e);
		}
		return disciplinaIncluida;
	}

	@Override
	public Disciplina update(Disciplina disciplina) {
		Disciplina disciplinaAlterada = null;
		try {
			disciplina.setResponsavel(usuarioService.findOne(disciplina.getResponsavel().getId()));

			if (!disciplina.getResponsavel().getIsProfessor()) {
				throw new BusinessException(ErrorCode.DISCIPLINA_NEED_A_PROFESSOR);
			}

			disciplinaAlterada = iDisciplinaRepo.save(disciplina);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_UPDATE, e);
		}
		return disciplinaAlterada;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iDisciplinaRepo.delete(findOne(id));
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_DELETE, e);
		}
		return retorno;
	}

	public Page<Disciplina> findPageableAndFiltered(int page, int count, String descricao) {
		Page<Disciplina> disciplina = null;
		try {
			disciplina = iDisciplinaRepo.findByNomeContaining(PageRequest.of(page, count), descricao);

		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_SEARCH, e);
		}

		return disciplina;
	}

	public Page<Disciplina> findPageable(int page, int count) {
		Page<Disciplina> disciplina = null;
		try {
			disciplina = iDisciplinaRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_SEARCH, e);
		}

		return disciplina;
	}

	public Iterable<Disciplina> findAll() {
		Iterable<Disciplina> disciplina = null;
		try {
			disciplina = iDisciplinaRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_SEARCH, e);
		}

		return disciplina;
	}

	public Disciplina findOne(int id) {
		Optional<Disciplina> disciplina = null;
		try {
			disciplina = iDisciplinaRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DISCIPLINA_SEARCH, e);
		}

		return disciplina.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.DISCIPLINA_NOT_FOUND));
	}
}