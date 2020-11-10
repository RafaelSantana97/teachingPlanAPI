package edu.planner.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 2064919400345011553L;

    private final Integer httpStatus;
    private final String message;
    private final String time;
}