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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable, IModel {

	private static final long serialVersionUID = -1526163791912460726L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 80)
	private String name;

	@ManyToMany
	@JoinTable(
			name = "COORD_COURSE",
			joinColumns = @JoinColumn(name = "coordinator_id", foreignKey = @ForeignKey(name="FK_USER")),
			inverseJoinColumns = @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name="FK_COURSE")))
	private List<User> coordinators = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "COURSE_SUBJECT",
			joinColumns = @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name="FK_SUBJECT")),
			inverseJoinColumns = @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name="FK_COURSE")))
	private List<Subject> subjects = new ArrayList<>();
}