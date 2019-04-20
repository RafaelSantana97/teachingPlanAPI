package edu.planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.models.Domain;
import edu.planner.repositories.IDomainRepo;

@RestController
@RequestMapping("/api/domain")
public class DomainController {

	@Autowired
	IDomainRepo iDomainRepo;

	@GetMapping("/all")
	public ResponseEntity<Iterable<Domain>> findAll() {
		Iterable<Domain> domain = null;
		try {
			domain = iDomainRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DOMAIN_SEARCH, e);
		}


		return domain != null ? ResponseEntity.ok(domain) : ResponseEntity.noContent().build();
	}
}