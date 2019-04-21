package edu.planner.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.Profile;
import edu.planner.enums.LevelDegree;
import edu.planner.interfaces.IModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable, IModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "is required")
	private String name;

	@NotEmpty(message = "is required")
	private String levelDegree;

	@NotEmpty(message = "is required")
	private String email;

	@JsonIgnore
	@NotEmpty(message = "is required")
	private String hashKey;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILES", foreignKey = @ForeignKey(name = "FK_USER"))
	@JsonIgnore
	private Set<Short> profiles = new HashSet<>();

	@Transient
	private Boolean isAdmin;

	@Transient
	private Boolean isCoordinator;

	@Transient
	private Boolean isTeacher;

	public User(Long id, String name, String levelDegree) {
		this.id = id;
		this.name = name;
		this.levelDegree = levelDegree;
	}

	public String getLevelDegree() {
		return LevelDegree.toEnum(levelDegree).getId();
	}

	public void setLevelDegree(String levelDegree) {
		this.levelDegree = LevelDegree.toEnum(levelDegree).getId();
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		profiles.add(profile.getId());
	}

	public Boolean getIsAdmin() {
		if (isAdmin == null) {
			isAdmin = profiles.stream().anyMatch(profile -> profile == Profile.ADMIN.getId());
		}

		return isAdmin;
	}

	public Boolean getIsCoordinator() {
		if (isCoordinator == null) {
			isCoordinator = profiles.stream().anyMatch(profile -> profile == Profile.COORDINATOR.getId());
		}

		return isCoordinator;
	}

	public Boolean getIsTeacher() {
		if (isTeacher == null) {
			isTeacher = profiles.stream().anyMatch(profile -> profile == Profile.TEACHER.getId());
		}

		return isTeacher;
	}
}