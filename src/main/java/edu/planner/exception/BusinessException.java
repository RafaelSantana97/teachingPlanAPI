package edu.planner.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1347727953511994344L;

	public BusinessException(ErrorCode code) {
		super(code.getMessage());
	}

	public BusinessException(ErrorCode code, Throwable cause) {
		super(code.getMessage(), cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}