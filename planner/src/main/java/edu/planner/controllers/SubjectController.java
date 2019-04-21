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

import edu.planner.dto.SubjectDTO;
import edu.planner.interfaces.IController;
import edu.planner.models.Subject;
import edu.planner.service.SubjectService;

@RestController
@RequestMapping("api/subject")
public class SubjectController implements IController<Subject, Subject> {

	@Autowired
	SubjectService subjectService;

	@PreAuthorize("hasAnyRole('COORDINATOR')")
	@Transactional
	@PostMapping
	public ResponseEntity<Subject> insert(@Valid @RequestBody Subject subject) {
		subject = subjectService.insert(subject);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDINATOR')")
	@Transactional
	@PutMapping
	public ResponseEntity<Subject> update(@Valid @RequestBody Subject subject) {
		subject = subjectService.update(subject);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('COORDINATOR')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = subjectService.delete(id);
		return result ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/interval/{page}/{count}/{description}")
	public ResponseEntity<Page<Subject>> findPageableAndFiltered(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("description") String description) {
		Page<Subject> subject = subjectService.findPageableAndFiltered(page, count, description);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}

	@GetMapping("/interval/{page}/{count}")
	public ResponseEntity<Page<Subject>> findPageable(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Page<Subject> subject = subjectService.findPageable(page, count);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}

	@GetMapping("/all")
	public ResponseEntity<Iterable<Subject>> findAll() {
		Iterable<Subject> subject = subjectService.findAll();
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}

	@GetMapping("/byCourse/{courseId}")
	public ResponseEntity<Iterable<SubjectDTO>> findAllSubjects(@PathVariable("courseId") Long courseId) {
		Iterable<SubjectDTO> subject = subjectService.findByCourse(courseId);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subject> findOne(@PathVariable("id") Long id) {
		Subject subject = subjectService.findOne(id);
		return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.noContent().build();
	}
}