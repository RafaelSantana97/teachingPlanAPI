package edu.planner.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 6894136634221083489L;

	@Email
	private String email;
	
	private String password;
}