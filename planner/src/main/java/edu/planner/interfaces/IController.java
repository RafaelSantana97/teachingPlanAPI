package edu.planner.interfaces;

import org.springframework.http.ResponseEntity;

public interface IController<T, D> {

	public ResponseEntity<T> insert(D d);

	public ResponseEntity<T> update(D d);

	public ResponseEntity<Boolean> delete(Long id);
}