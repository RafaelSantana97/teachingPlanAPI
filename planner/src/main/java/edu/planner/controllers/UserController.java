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

import edu.planner.enums.Profile;
import edu.planner.interfaces.IController;
import edu.planner.models.User;
import edu.planner.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController implements IController<User, User> {

	@Autowired
	UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody User user) {
		user = userService.insert(user);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@PutMapping
	public ResponseEntity<User> update(@Valid @RequestBody User user) {
		user = userService.update(user);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = userService.delete(id);
		return result ? ResponseEntity.ok(result) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/interval/{page}/{count}/{description}")
	public ResponseEntity<Page<User>> findPageableAndFiltered(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("description") String description) {
		Page<User> user = userService.findPageableAndFiltered(page, count, description);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/interval/{page}/{count}")
	public ResponseEntity<Page<User>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
		Page<User> user = userService.findPageable(page, count);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/interval/{page}/{count}/teacher/{description}")
	public ResponseEntity<Page<User>> findPageableAndFilteredTeacher(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("description") String description) {
		Page<User> user = userService.findPageableAndFilteredProfile(page, count, Profile.TEACHER.getId(), description);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/interval/{page}/{count}/teacher")
	public ResponseEntity<Page<User>> findPageableByTeacher(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Page<User> user = userService.findPageableByProfile(page, count, Profile.TEACHER.getId());
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/interval/{page}/{count}/coordinator/{description}")
	public ResponseEntity<Page<User>> findPageableAndFilteredCoord(@PathVariable("page") int page,
			@PathVariable("count") int count, @PathVariable("description") String description) {
		Page<User> user = userService.findPageableAndFilteredProfile(page, count, Profile.COORDINATOR.getId(), description);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/interval/{page}/{count}/coordinator")
	public ResponseEntity<Page<User>> findPageableByCoord(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Page<User> user = userService.findPageableByProfile(page, count, Profile.COORDINATOR.getId());
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<User>> findAll() {
		Iterable<User> user = userService.findAll();
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}
}