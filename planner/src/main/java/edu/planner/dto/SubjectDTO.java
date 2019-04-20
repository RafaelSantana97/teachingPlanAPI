package edu.planner.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import edu.planner.models.Subject;
import edu.planner.models.User;
import edu.planner.models.validation.ModelConstraint;

public class SubjectDTO {

	private Integer id;

	@NotEmpty(message = "is required")
	@Size(min = 5, max = 80, message = "Must be between 5 and 80 characters")
	private String name;

	@NotEmpty(message = "is required")
	private String type;

	@ModelConstraint
	private User responsible;

	private boolean checked = false;

	public SubjectDTO() {

	}

	public SubjectDTO(Integer id, String name, String type, User responsible, boolean checked) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.responsible = responsible;
		this.checked = checked;
	}

	public static SubjectDTO toDTO(Subject subject, boolean checked) {
		return new SubjectDTO(subject.getId(), subject.getName(), subject.getType(), subject.getResponsible(), checked);
	}

	public static Subject fromDTO(SubjectDTO subject) {
		return new Subject(subject.getId(), subject.getName(), subject.getType(), subject.getResponsible());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}