package edu.planner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.planner.interfaces.IModel;

@Entity
public class Course implements Serializable, IModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	@ManyToMany
	@JoinTable(name = "COORD_COURSE",
		joinColumns = @JoinColumn(name = "coordinator"),
		inverseJoinColumns = @JoinColumn(name = "id")
	)
	private List<User> coordinators = new ArrayList<User>();

	@ManyToMany
	@JoinTable(name = "COURSE_SUBJECT",
		joinColumns = @JoinColumn(name = "subject"),
		inverseJoinColumns = @JoinColumn(name = "id")
	)
	private List<Subject> subjects = new ArrayList<Subject>();

	public Course() {
	}

	public Course(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	public List<User> getCoordinators() {
		return coordinators;
	}

	public void setCoordinators(List<User> coordinators) {
		this.coordinators = coordinators;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}