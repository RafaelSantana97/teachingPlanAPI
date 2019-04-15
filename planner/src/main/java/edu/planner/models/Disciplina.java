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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.TipoDisciplina;
import edu.planner.interfaces.IModel;
import edu.planner.models.validation.ModelConstraint;

@Entity
public class Disciplina implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 80, message = "Necessário ter entre 5 e 80 caracteres")
	private String nome;

	@Column
	@NotEmpty(message = "Preenchimento obrigatório")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "responsavel", nullable = false)
	@ModelConstraint
	private Usuario responsavel;

	@OneToMany(mappedBy = "disciplina")
	@JsonBackReference
	private List<Turma> turmas = new ArrayList<Turma>();

	@ManyToMany(mappedBy = "disciplinas")
	@JsonIgnore
	private List<Curso> cursos = new ArrayList<Curso>();

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

	public String getTipo() {
		return TipoDisciplina.toEnum(tipo).getId();
	}

	public void setTipo(TipoDisciplina tipo) {
		this.tipo = tipo.getId();
	}

	public void setTipo(String tipo) {
		if (tipo == null || tipo.isEmpty())
			return;
		this.tipo = TipoDisciplina.toEnum(tipo).getId();
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}