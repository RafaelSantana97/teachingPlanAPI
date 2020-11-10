package edu.planner.exception;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = -1284191990113775796L;

	public AuthorizationException(String message) {
		super(message);
	}
}