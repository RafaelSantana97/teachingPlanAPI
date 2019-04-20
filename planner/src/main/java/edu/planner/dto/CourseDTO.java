package edu.planner.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import edu.planner.models.Course;
import edu.planner.models.User;

public class CourseDTO {

	private Integer id;

	@NotEmpty(message = "is required")
	@Size(min = 5, max = 40, message = "Must be between 5 and 40 characters")
	private String name;

	private List<User> coordinators = new ArrayList<User>();

	private List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();

	public CourseDTO() {

	}

	public CourseDTO(Integer id, String name, List<User> coordinators) {
		this.id = id;
		this.name = name;
		this.coordinators = coordinators;
	}

	public static CourseDTO toDTO(Course course) {
		return new CourseDTO(course.getId(), course.getName(), course.getCoordinators());
	}

	public static Course fromDTO(CourseDTO subject) {
		return new Course(subject.getId(), subject.getName());
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

	public List<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
}