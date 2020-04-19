package edu.planner.controllers;

import javax.validation.Valid;

import edu.planner.dto.ClassDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import edu.planner.models.Class;
import edu.planner.service.ClassService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @PostMapping
    public ResponseEntity<Class> insert(@Valid @RequestBody ClassDTO classDTO) {
        Class clazz = classService.insert(ClassDTO.fromDTO(classDTO));
        return clazz != null ? ResponseEntity.ok(clazz) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @PutMapping
    public ResponseEntity<Class> update(@Valid @RequestBody ClassDTO classDTO) {
        Class clazz = classService.update(ClassDTO.fromDTO(classDTO));
        return clazz != null ? ResponseEntity.ok(clazz) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/interval/{page}/{count}/{descriptionSubject}")
    public ResponseEntity<Page<Class>> findPageableAndFiltered(@PathVariable("page") int page, @PathVariable("count") int count,
                                                               @PathVariable("descriptionSubject") String descriptionSubject) {
        Page<Class> clazz = classService.findPageableAndFiltered(page, count, descriptionSubject);
        return clazz != null ? ResponseEntity.ok(clazz) : ResponseEntity.noContent().build();
    }

    @GetMapping("/interval/{page}/{count}")
    public ResponseEntity<Page<Class>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
        Page<Class> clazz = classService.findPageable(page, count);
        return clazz != null ? ResponseEntity.ok(clazz) : ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Class>> findAll() {
        Iterable<Class> clazz = classService.findAll();
        return clazz != null ? ResponseEntity.ok(clazz) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> findOne(@PathVariable("id") Long id) {
        Class clazz = classService.findOne(id);
        return clazz != null ? ResponseEntity.ok(clazz) : ResponseEntity.noContent().build();
    }
}