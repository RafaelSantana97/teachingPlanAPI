package edu.planner.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(ErrorCode code) {
		super(code.getMessage());
	}

	public ObjectNotFoundException(ErrorCode code, Throwable cause) {
		super(code.getMessage(), cause);
	}
}