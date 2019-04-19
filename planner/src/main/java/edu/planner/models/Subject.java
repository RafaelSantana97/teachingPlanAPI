package edu.planner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.SubjectType;
import edu.planner.interfaces.IModel;
import edu.planner.models.validation.ModelConstraint;

@Entity
public class Subject implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "is required")
	@Size(min = 5, max = 80, message = "Must be between 5 and 80 characters")
	private String name;

	@Column
	@NotEmpty(message = "is required")
	private String type;

	@ManyToOne
	@JoinColumn(name = "responsible", nullable = false)
	@ModelConstraint
	private User responsible;

	@OneToMany(mappedBy = "subject")
	@JsonBackReference
	private List<Class> classes = new ArrayList<Class>();

	@ManyToMany(mappedBy = "subjects")
	@JsonIgnore
	private List<Course> courses = new ArrayList<Course>();

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
		return SubjectType.toEnum(type).getId();
	}

	public void setType(SubjectType type) {
		this.type = type.getId();
	}

	public void setType(String type) {
		if (type == null || type.isEmpty())
			return;
		this.type = SubjectType.toEnum(type).getId();
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}