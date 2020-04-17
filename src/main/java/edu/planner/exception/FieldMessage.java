package edu.planner.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1747387976269530737L;

	private final String fieldName;
	private final String message;
}