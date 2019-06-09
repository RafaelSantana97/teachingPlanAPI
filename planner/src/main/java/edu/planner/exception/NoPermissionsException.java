package edu.planner.exception;

import org.springframework.security.core.AuthenticationException;

public class NoPermissionsException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public NoPermissionsException(ErrorCode code) {
		super(code.getMessage());
	}

	public NoPermissionsException(ErrorCode code, Throwable cause) {
		super(code.getMessage(), cause);
	}

	public NoPermissionsException(String message) {
		super(message);
	}

	public NoPermissionsException(String message, Throwable cause) {
		super(message, cause);
	}
}