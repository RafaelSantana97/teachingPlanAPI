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
import edu.planner.models.Class;
import edu.planner.service.ClassService;

@RestController
@RequestMapping("api/class")
public class ClassController implements IController<Class, Class> {

	@Autowired
	ClassService classService;

	@PreAuthorize("hasAnyRole('COORDINATOR')")
	@Transactional
	@PostMapping
	public ResponseEntity<Class> insert(@Valid @RequestBody Class _class) {
		_class = classService.insert(_class);
		return _class != null ? ResponseEntity.ok(_class) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDINATOR')")
	@Transactional
	@PutMapping
	public ResponseEntity<Class> update(@Valid @RequestBody Class _class) {
		_class = classService.update(_class);
		return _class != null ? ResponseEntity.ok(_class) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDINATOR')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean result = classService.delete(id);
		return result ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/interval/{page}/{count}/{descriptionSubject}")
	public ResponseEntity<Page<Class>> findPageableAndFiltered(@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("descriptionSubject") String descriptionSubject) {
		Page<Class> _class = classService.findPageableAndFiltered(page, count, descriptionSubject);
		return _class != null ? ResponseEntity.ok(_class) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/interval/{page}/{count}")
	public ResponseEntity<Page<Class>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
		Page<Class> _class = classService.findPageable(page, count);
		return _class != null ? ResponseEntity.ok(_class) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Class>> findAll() {
		Iterable<Class> _class = classService.findAll();
		return _class != null ? ResponseEntity.ok(_class) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Class> findOne(@PathVariable("id") int id) {
		Class _class = classService.findOne(id);
		return _class != null ? ResponseEntity.ok(_class) : ResponseEntity.noContent().build();
	}
}