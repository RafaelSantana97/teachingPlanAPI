package edu.planner.exception;

import org.springframework.security.core.AuthenticationException;

public class NoPermissionsException extends AuthenticationException {

	private static final long serialVersionUID = -1502130849530536520L;

	public NoPermissionsException(ErrorCode code) {
		super(code.getMessage());
	}
}