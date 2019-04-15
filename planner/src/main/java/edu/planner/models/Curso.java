package edu.planner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.planner.interfaces.IModel;

@Entity
public class Curso implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "COORD_CURSO",
		joinColumns = @JoinColumn(name = "coordenador"),
		inverseJoinColumns = @JoinColumn(name = "id")
	)
	private List<Usuario> coordenadores = new ArrayList<Usuario>();

	@ManyToMany
	@JoinTable(name = "CURSO_DISCIPLINA",
		joinColumns = @JoinColumn(name = "disciplina"),
		inverseJoinColumns = @JoinColumn(name = "id")
	)
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getCoordenadores() {
		return coordenadores;
	}

	public void setCoordenadores(List<Usuario> coordenadores) {
		this.coordenadores = coordenadores;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}