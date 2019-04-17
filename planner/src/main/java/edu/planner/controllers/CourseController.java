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
import edu.planner.models.Course;
import edu.planner.service.CourseService;

@RestController
@RequestMapping("api/course")
public class CourseController implements IController<Course> {

	@Autowired
	CourseService courseService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PostMapping
	public ResponseEntity<Course> insert(@RequestBody Course course) {
		course = courseService.insert(course);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PutMapping
	public ResponseEntity<Course> update(@RequestBody Course course) {
		course = courseService.update(course);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = courseService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/intervalo/{page}/{count}/{description}")
	public ResponseEntity<Page<Course>> findPageableAndFiltered(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("description") String description) {
		Page<Course> course = courseService.findPageableAndFiltered(page, count, description);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
	}

	@GetMapping("/intervalo/{page}/{count}")
	public ResponseEntity<Page<Course>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
		Page<Course> course = courseService.findPageable(page, count);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Course>> findAll() {
		Iterable<Course> course = courseService.findAll();
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findOne(@PathVariable("id") int id) {
		Course course = courseService.findOne(id);
		return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
	}
}