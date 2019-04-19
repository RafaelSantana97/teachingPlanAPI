package edu.planner.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import edu.planner.enums.Period;
import edu.planner.enums.Semester;
import edu.planner.interfaces.IModel;
import edu.planner.models.validation.ModelConstraint;

@Entity
public class Class implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String code;

	private Integer period;

	@ManyToOne
	@JoinColumn(name = "subject", nullable = false)
	@ModelConstraint
	private Subject subject;

	private String semester;

	@Digits(fraction = 0, integer = 4, message = "Invalid value")
	private Integer year;

	@ManyToOne
	@JoinColumn(name = "teacher", nullable = false)
	@ModelConstraint
	private User teacher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		if (period == null)
			return;
		this.period = Period.toEnum(period).getId();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		if (semester == null)
			return;
		this.semester = Semester.toEnum(semester).getId();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
}