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
import edu.planner.models.Usuario;
import edu.planner.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController implements IController<Usuario> {

	@Autowired
	UsuarioService usuarioService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
		usuario = usuarioService.insert(usuario);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		usuario = usuarioService.update(usuario);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = usuarioService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/intervalo/{page}/{count}/{descricao}")
	public ResponseEntity<Page<Usuario>> findPageableAndFiltered(@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("descricao") String descricao) {
		Page<Usuario> usuario = usuarioService.findPageableAndFiltered(page, count, descricao);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/intervalo/{page}/{count}")
	public ResponseEntity<Page<Usuario>> findPageable(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Page<Usuario> usuario = usuarioService.findPageable(page, count);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Usuario>> findAll() {
		Iterable<Usuario> usuario = usuarioService.findAll();
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findOne(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.findOne(id);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.noContent().build();
	}
}