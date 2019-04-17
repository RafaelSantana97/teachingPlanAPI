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

import edu.planner.enums.Perfil;
import edu.planner.enums.Title;
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
	private String titulacao;

	@NotEmpty(message = "is required")
	private String email;

	@JsonIgnore
	@NotEmpty(message = "is required")
	private String hashKey;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	@JsonIgnore
	private Set<Integer> perfis = new HashSet<>();

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

	public String getTitulacao() {
		return Title.toEnum(titulacao).getId();
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = Title.toEnum(titulacao).getId();
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getId());
	}

	public Boolean getIsAdmin() {
		if (isAdmin == null) {
			isAdmin = perfis.stream().anyMatch(perfil -> perfil == Perfil.ADMIN.getId());
		}

		return isAdmin;
	}

	public Boolean getIsCoordinator() {
		if (isCoordinator == null) {
			isCoordinator = perfis.stream().anyMatch(perfil -> perfil == Perfil.COORDINATOR.getId());
		}

		return isCoordinator;
	}

	public Boolean getIsTeacher() {
		if (isTeacher == null) {
			isTeacher = perfis.stream().anyMatch(perfil -> perfil == Perfil.TEACHER.getId());
		}

		return isTeacher;
	}
}