package edu.planner.controllers;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import edu.planner.dto.SubjectDTO;
import edu.planner.dto.SubjectInsertDTO;
import edu.planner.models.Subject;
import edu.planner.service.SubjectService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @PostMapping
    public ResponseEntity<Subject> insert(@Valid @RequestBody SubjectInsertDTO subject) {
        Subject subjectIncluded = subjectService.insert(subject);
        return subjectIncluded != null ? ResponseEntity.ok(subjectIncluded) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @PutMapping
    public ResponseEntity<Subject> update(@Valid @RequestBody SubjectInsertDTO subject) {
        Subject subjectAltered = subjectService.update(subject);
        return subjectAltered != null ? ResponseEntity.ok(subjectAltered) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.ok(true);
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