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
import edu.planner.models.UserType;
import edu.planner.service.UserTypeService;

@RestController
@RequestMapping("api/userType")
public class UserTypeController implements IController<UserType> {

	@Autowired
	UserTypeService userTypeService;

	@Transactional
	@PostMapping
	public ResponseEntity<UserType> insert(@RequestBody UserType userType) {
		userType = userTypeService.insert(userType);
		return userType != null ? ResponseEntity.ok(userType) : ResponseEntity.noContent().build();
	}

	@Transactional
	@PutMapping
	public ResponseEntity<UserType> update(@RequestBody UserType userType) {
		userType = userTypeService.update(userType);
		return userType != null ? ResponseEntity.ok(userType) : ResponseEntity.noContent().build();
	}

	@Transactional
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		Boolean retorno = userTypeService.delete(id);
		return retorno ? ResponseEntity.ok(retorno) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/intervalo/{page}/{count}/{description}")
	public ResponseEntity<Page<UserType>> findPageableAndFiltered(@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("description") String description) {
		Page<UserType> userType = userTypeService.findPageableAndFiltered(page, count, description);
		return userType != null ? ResponseEntity.ok(userType) : ResponseEntity.noContent().build();
	}
}