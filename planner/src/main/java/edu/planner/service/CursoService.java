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
import edu.planner.models.Curso;
import edu.planner.repositories.ICursoRepo;

@Service
public class CursoService implements IService<Curso> {

	@Autowired
	ICursoRepo iCursoRepo;

	@Override
	public Curso insert(Curso curso) {
		Curso cursoIncluido = null;
		try {
			cursoIncluido = iCursoRepo.save(curso);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_SAVE, e);
		}
		return cursoIncluido;
	}

	@Override
	public Curso update(Curso curso) {
		Curso cursoAlterado = null;
		try {
			cursoAlterado = iCursoRepo.save(curso);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_UPDATE, e);
		}
		return cursoAlterado;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iCursoRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.CURSO_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_DELETE, e);
		}
		return retorno;
	}

	public Page<Curso> findPageableAndFiltered(int page, int count, String descricao) {
		Page<Curso> curso = null;
		try {
			curso = iCursoRepo.findByNomeContaining(PageRequest.of(page, count), descricao);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_SEARCH, e);
		}

		return curso;
	}

	public Page<Curso> findPageable(int page, int count) {
		Page<Curso> curso = null;
		try {
			curso = iCursoRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_SEARCH, e);
		}

		return curso;
	}

	public Iterable<Curso> findAll() {
		Iterable<Curso> curso = null;
		try {
			curso = iCursoRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_SEARCH, e);
		}

		return curso;
	}

	public Curso findOne(int id) {
		Optional<Curso> curso = null;
		try {
			curso = iCursoRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.CURSO_SEARCH, e);
		}

		return curso.orElseThrow(() -> new BusinessException(ErrorCode.CURSO_NOT_FOUND));
	}
}