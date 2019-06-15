package edu.planner.exception;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(ErrorCode code) {
		super(code.getMessage());
	}

	public AuthorizationException(ErrorCode code, Throwable cause) {
		super(code.getMessage(), cause);
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
}