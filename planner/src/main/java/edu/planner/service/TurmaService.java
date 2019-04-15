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
import edu.planner.models.Turma;
import edu.planner.repositories.ITurmaRepo;

@Service
public class TurmaService implements IService<Turma> {

	@Autowired
	ITurmaRepo iTurmaRepo;

	@Autowired
	DisciplinaService disciplinaService;

	@Autowired
	UsuarioService usuarioService;

	@Override
	public Turma insert(Turma turma) {
		Turma turmaIncluida = null;
		try {
			turma.setProfessor(usuarioService.findOne(turma.getProfessor().getId()));

			if (!turma.getProfessor().getIsProfessor()) {
				throw new BusinessException(ErrorCode.TURMA_NEED_A_PROFESSOR);
			}

			turma.setDisciplina(disciplinaService.findOne(turma.getDisciplina().getId()));

			turmaIncluida = iTurmaRepo.save(turma);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_SAVE, e);
		}
		return turmaIncluida;
	}

	@Override
	public Turma update(Turma turma) {
		Turma turmaAlterada = null;
		try {
			turma.setProfessor(usuarioService.findOne(turma.getProfessor().getId()));

			if (!turma.getProfessor().getIsProfessor()) {
				throw new BusinessException(ErrorCode.TURMA_NEED_A_PROFESSOR);
			}

			turmaAlterada = iTurmaRepo.save(turma);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_UPDATE, e);
		}
		return turmaAlterada;
	}

	@Override
	public Boolean delete(int id) {
		Boolean retorno = false;

		try {
			iTurmaRepo.deleteById(id);
			retorno = true;
		} catch (ConstraintViolationException e) {
			throw new BusinessException(ErrorCode.TURMA_DELETE_VIOLATION, e);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_DELETE, e);
		}
		return retorno;
	}

	public Page<Turma> findPageableAndFiltered(int page, int count, String descricao) {
		Page<Turma> turma = null;
		try {
			turma = iTurmaRepo.findByDisciplinaNomeContaining(PageRequest.of(page, count), descricao);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_SEARCH, e);
		}

		return turma;
	}

	public Page<Turma> findPageable(int page, int count) {
		Page<Turma> turma = null;
		try {
			turma = iTurmaRepo.findAll(PageRequest.of(page, count));
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_SEARCH, e);
		}

		return turma;
	}

	public Iterable<Turma> findAll() {
		Iterable<Turma> turma = null;
		try {
			turma = iTurmaRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_SEARCH, e);
		}

		return turma;
	}

	public Turma findOne(int id) {
		Optional<Turma> turma = null;
		try {
			turma = iTurmaRepo.findById(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.TURMA_SEARCH, e);
		}

		return turma.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.TURMA_NOT_FOUND));
	}
}