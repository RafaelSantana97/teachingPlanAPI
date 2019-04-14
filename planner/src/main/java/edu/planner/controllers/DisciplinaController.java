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
import edu.planner.models.Disciplina;
import edu.planner.service.DisciplinaService;

@RestController
@RequestMapping("api/disciplina")
public class DisciplinaController implements IController<Disciplina> {

	@Autowired
	DisciplinaService disciplinaService;

	@PreAuthorize("hasAnyRole('COORDENADOR')")
	@Transactional
	@PostMapping
	public ResponseEntity<Disciplina> insert(@Valid @RequestBody Disciplina disciplina) {
		disciplina = disciplinaService.insert(disciplina);
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDENADOR')")
	@Transactional
	@PutMapping
	public ResponseEntity<Disciplina> update(@Valid @RequestBody Disciplina disciplina) {
		disciplina = disciplinaService.update(disciplina);
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDENADOR')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = disciplinaService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/intervalo/{page}/{count}/{descricao}")
	public ResponseEntity<Page<Disciplina>> findPageableAndFiltered(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("descricao") String descricao) {
		Page<Disciplina> disciplina = disciplinaService.findPageableAndFiltered(page, count, descricao);
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.noContent().build();
	}

	@GetMapping("/intervalo/{page}/{count}")
	public ResponseEntity<Page<Disciplina>> findPageable(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Page<Disciplina> disciplina = disciplinaService.findPageable(page, count);
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Disciplina>> findAll() {
		Iterable<Disciplina> disciplina = disciplinaService.findAll();
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> findOne(@PathVariable("id") int id) {
		Disciplina disciplina = disciplinaService.findOne(id);
		return disciplina != null ? ResponseEntity.ok(disciplina) : ResponseEntity.noContent().build();
	}
}