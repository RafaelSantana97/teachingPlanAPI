package edu.planner.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.planner.models.Dominio;

public interface IDominioRepo extends PagingAndSortingRepository<Dominio, Integer> {

}