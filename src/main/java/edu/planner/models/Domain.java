package edu.planner.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Domain implements Serializable {

	private static final long serialVersionUID = -5463553494039919064L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String description;

	@Column(nullable = false)
	private String value1;

	@Column(nullable = false, length = 10)
	private String abbreviation;

	private String value2;
}