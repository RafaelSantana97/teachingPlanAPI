package edu.planner.models;

import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
			name = "coord_course",
			joinColumns = @JoinColumn(name = "coordinator_id", foreignKey = @ForeignKey(name="FK_USER")),
			inverseJoinColumns = @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name="FK_COURSE")))
	private List<User> coordinators = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "course_subject",
			joinColumns = @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name="FK_SUBJECT")),
			inverseJoinColumns = @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name="FK_COURSE")))
	private List<Subject> subjects = new ArrayList<>();
}