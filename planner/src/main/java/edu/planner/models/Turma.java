package edu.planner.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import edu.planner.enums.Periodo;
import edu.planner.enums.Semestre;
import edu.planner.interfaces.IModel;
import edu.planner.models.validation.ModelConstraint;

@Entity
public class Turma implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String codigo;

	private Integer periodo;

	@ManyToOne
	@JoinColumn(name = "disciplina", nullable = false)
	@ModelConstraint
	private Disciplina disciplina;

	private String semestre;

	@Digits(fraction = 0, integer = 4, message = "Valor inválido")
	private Integer ano;

	@ManyToOne
	@JoinColumn(name = "professor", nullable = false)
	@ModelConstraint
	private Usuario professor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Integer periodo) {
		if (periodo == null)
			return;
		this.periodo = Periodo.toEnum(periodo).getId();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return this.semestre;
	}

	public void setSemestre(String semestre) {
		if (semestre == null)
			return;
		this.semestre = Semestre.toEnum(semestre).getId();
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}
}