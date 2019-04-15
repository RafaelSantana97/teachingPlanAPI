package edu.planner.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.planner.interfaces.IController;
import edu.planner.models.Turma;
import edu.planner.service.TurmaService;

@RestController
@RequestMapping("api/turma")
public class TurmaController implements IController<Turma> {

	@Autowired
	TurmaService turmaService;

	@PreAuthorize("hasAnyRole('COORDENADOR')")
	@Transactional
	@PostMapping
	public ResponseEntity<Turma> insert(@Valid @RequestBody Turma turma) {
		turma = turmaService.insert(turma);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDENADOR')")
	@Transactional
	@PutMapping
	public ResponseEntity<Turma> update(@Valid @RequestBody Turma turma) {
		turma = turmaService.update(turma);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDENADOR')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = turmaService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/intervalo/{page}/{count}/{descricaoDisciplina}")
	public ResponseEntity<Page<Turma>> findPageableAndFiltered(@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("descricaoDisciplina") String descricaoDisciplina) {
		Page<Turma> turma = turmaService.findPageableAndFiltered(page, count, descricaoDisciplina);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/intervalo/{page}/{count}")
	public ResponseEntity<Page<Turma>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
		Page<Turma> turma = turmaService.findPageable(page, count);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Turma>> findAll() {
		Iterable<Turma> turma = turmaService.findAll();
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> findOne(@PathVariable("id") int id) {
		Turma turma = turmaService.findOne(id);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.noContent().build();
	}
}