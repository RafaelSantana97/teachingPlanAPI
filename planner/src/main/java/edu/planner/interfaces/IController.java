package edu.planner.interfaces;

import org.springframework.http.ResponseEntity;

public interface IController<T> {

	public ResponseEntity<T> insert(T t);

	public ResponseEntity<T> update(T t);

	public ResponseEntity<Boolean> delete(int id);
}