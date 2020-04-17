package edu.planner.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
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
import edu.planner.dto.CourseDTO;
import edu.planner.models.Course;
import edu.planner.service.CourseService;

@RestController
@RequestMapping("api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @Transactional
    @PostMapping
    public ResponseEntity<Course> insert(@Valid @RequestBody CourseDTO course) {
        Course courseIncluded = courseService.insert(course);
        return courseIncluded != null ? ResponseEntity.ok(courseIncluded) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @Transactional
    @PutMapping
    public ResponseEntity<Course> update(@Valid @RequestBody CourseDTO course) {
        Course courseAltered = courseService.update(course);
        return courseAltered != null ? ResponseEntity.ok(courseAltered) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        Boolean result = courseService.delete(id);
        return result ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/interval/{page}/{count}/{description}")
    public ResponseEntity<Page<Course>> findPageableAndFiltered(@PathVariable("page") int page,
                                                                @PathVariable("count") int count, @PathVariable("description") String description) {
        Page<Course> course = courseService.findPageableAndFiltered(page, count, description);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
    }

    @GetMapping("/interval/{page}/{count}")
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
    public ResponseEntity<CourseDTO> findOne(@PathVariable("id") Long id) {
        CourseDTO course = courseService.findOne(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.noContent().build();
    }
}