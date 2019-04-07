package edu.planner.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ErrorCode code;

	public BusinessException(ErrorCode code) {
		super();
		this.code = code;
	}

	public BusinessException(ErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(ErrorCode code, Throwable cause) {
		super(cause);
		this.code = code;
	}
	
	public BusinessException(ErrorCode code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ErrorCode getCode() {
		return this.code;
	}
}