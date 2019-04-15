package edu.planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.models.Dominio;
import edu.planner.repositories.IDominioRepo;

@RestController
@RequestMapping("/api/dominio")
public class DominioController {

	@Autowired
	IDominioRepo iDominioRepo;

	@GetMapping
	public ResponseEntity<Iterable<Dominio>> findAll() {
		Iterable<Dominio> dominio = null;
		try {
			dominio = iDominioRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.DOMINIO_SEARCH, e);
		}


		return dominio != null ? ResponseEntity.ok(dominio) : ResponseEntity.noContent().build();
	}
}