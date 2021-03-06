package edu.planner.controllers;

import edu.planner.dto.CourseDTO;
import edu.planner.models.Course;
import edu.planner.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @PostMapping
    public ResponseEntity<Course> insert(@Valid @RequestBody CourseDTO course) {
        Course courseIncluded = courseService.insert(course);
        return courseIncluded != null ? ResponseEntity.ok(courseIncluded) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('COORDINATOR')")
    @PutMapping
    public ResponseEntity<Course> update(@Valid @RequestBody CourseDTO course) {
        Course courseAltered = courseService.update(course);
        return courseAltered != null ? ResponseEntity.ok(courseAltered) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok(true);
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