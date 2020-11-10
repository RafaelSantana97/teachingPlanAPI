package edu.planner.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8730730429020759702L;

	public ObjectNotFoundException(ErrorCode code) {
		super(code.getMessage());
	}
}