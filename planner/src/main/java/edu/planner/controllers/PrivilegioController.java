package edu.planner.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.planner.interfaces.IController;
import edu.planner.models.Privilegio;
import edu.planner.service.PrivilegioService;

@RestController
@RequestMapping("api/privilegio")
public class PrivilegioController implements IController<Privilegio> {

	@Autowired
	PrivilegioService privilegioService;

	@Transactional
	@PostMapping
	public ResponseEntity<Privilegio> insert(@RequestBody Privilegio privilegio) {
		privilegio = privilegioService.insert(privilegio);
		return privilegio != null ? ResponseEntity.ok(privilegio) : ResponseEntity.noContent().build();
	}

	@Transactional
	@PutMapping
	public ResponseEntity<Privilegio> update(@RequestBody Privilegio privilegio) {
		privilegio = privilegioService.update(privilegio);
		return privilegio != null ? ResponseEntity.ok(privilegio) : ResponseEntity.noContent().build();
	}

	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = privilegioService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/intervalo/{page}/{count}/{description}")
	public ResponseEntity<Page<Privilegio>> findPageableAndFiltered(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("description") String description) {
		Page<Privilegio> privilegio = privilegioService.findPageableAndFiltered(page, count, description);
		return privilegio != null ? ResponseEntity.ok(privilegio) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Privilegio>> findAll() {
		Iterable<Privilegio> privilegio = privilegioService.findAll();
		return privilegio != null ? ResponseEntity.ok(privilegio) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Privilegio> findOne(@PathVariable("id") int id) {
		Privilegio privilegio = privilegioService.findOne(id);
		return privilegio != null ? ResponseEntity.ok(privilegio) : ResponseEntity.noContent().build();
	}
}