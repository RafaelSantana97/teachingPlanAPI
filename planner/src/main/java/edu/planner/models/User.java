package edu.planner.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.Profile;
import edu.planner.enums.LevelDegree;
import edu.planner.interfaces.IModel;

@Entity
public class User implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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
	@CollectionTable(name = "PROFILES")
	@JsonIgnore
	private Set<Integer> profiles = new HashSet<>();

	@Transient
	private Boolean isAdmin;

	@Transient
	private Boolean isCoordinator;

	@Transient
	private Boolean isTeacher;

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

	public String getLevelDegree() {
		return LevelDegree.toEnum(levelDegree).getId();
	}

	public void setLevelDegree(String levelDegree) {
		this.levelDegree = LevelDegree.toEnum(levelDegree).getId();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
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