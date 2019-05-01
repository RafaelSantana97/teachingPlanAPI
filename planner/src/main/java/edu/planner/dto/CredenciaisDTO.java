package edu.planner.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredenciaisDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Email
	private String email;
	
	private String senha;
}