package edu.planner.controllers;

import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.models.Domain;
import edu.planner.repositories.IDomainRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/domain")
@RequiredArgsConstructor
public class DomainController {

    private final IDomainRepo iDomainRepo;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Domain>> findAll() {
        Iterable<Domain> domain;
        try {
            domain = iDomainRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DOMAIN_SEARCH, e);
        }

        return ResponseEntity.ok(domain);
    }
}