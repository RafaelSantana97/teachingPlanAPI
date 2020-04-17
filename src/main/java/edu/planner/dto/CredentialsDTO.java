package edu.planner.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 6894136634221083489L;

	@Email
	private String email;
	
	private String password;
}