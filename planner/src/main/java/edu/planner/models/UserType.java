package edu.planner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	@ManyToMany
	@JoinTable(name = "PRIVILEGIO_USER_TYPE",
		joinColumns = @JoinColumn(name = "privilegio"),
		inverseJoinColumns = @JoinColumn(name = "id")
	)
	private List<Privilegio> privilegios = new ArrayList<Privilegio>();
	
	
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

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}
}