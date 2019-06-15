package edu.planner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.SubjectType;
import edu.planner.interfaces.IModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject implements Serializable, IModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 80)
	private String name;

	@Column(nullable = false, length = 1)
	private String type;

	@ManyToOne
	@JoinColumn(name = "responsible", nullable = false, foreignKey = @ForeignKey(name = "FK_USER"))
	private User responsible;

	@OneToMany(mappedBy = "subject")
	@JsonBackReference
	private List<Class> classes = new ArrayList<Class>();

	@ManyToMany(mappedBy = "subjects")
	@JsonIgnore
	private List<Course> courses = new ArrayList<Course>();

	public Subject(Long id, String name, String type, User responsible) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.responsible = responsible;
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
}