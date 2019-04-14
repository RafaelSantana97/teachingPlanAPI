package edu.planner.controllers;

import javax.transaction.Transactional;

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
import edu.planner.models.Curso;
import edu.planner.service.CursoService;

@RestController
@RequestMapping("api/curso")
public class CursoController implements IController<Curso> {

	@Autowired
	CursoService cursoService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PostMapping
	public ResponseEntity<Curso> insert(@RequestBody Curso curso) {
		curso = cursoService.insert(curso);
		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PutMapping
	public ResponseEntity<Curso> update(@RequestBody Curso curso) {
		curso = cursoService.update(curso);
		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = cursoService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/intervalo/{page}/{count}/{descricao}")
	public ResponseEntity<Page<Curso>> findPageableAndFiltered(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("descricao") String descricao) {
		Page<Curso> curso = cursoService.findPageableAndFiltered(page, count, descricao);
		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.noContent().build();
	}

	@GetMapping("/intervalo/{page}/{count}")
	public ResponseEntity<Page<Curso>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
		Page<Curso> curso = cursoService.findPageable(page, count);
		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Curso>> findAll() {
		Iterable<Curso> curso = cursoService.findAll();
		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Curso> findOne(@PathVariable("id") int id) {
		Curso curso = cursoService.findOne(id);
		return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.noContent().build();
	}
}