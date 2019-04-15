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
import edu.planner.enums.Titulacao;
import edu.planner.interfaces.IModel;

@Entity
public class Usuario implements Serializable, IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String titulacao;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String email;

	@JsonIgnore
	@NotEmpty(message = "Preenchimento obrigatório")
	private String hashKey;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	@JsonIgnore
	private Set<Integer> perfis = new HashSet<>();

	@Transient
	private Boolean isAdmin;

	@Transient
	private Boolean isCoordenador;

	@Transient
	private Boolean isProfessor;

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

	public String getTitulacao() {
		return Titulacao.toEnum(titulacao).getId();
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = Titulacao.toEnum(titulacao).getId();
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

	public Boolean getIsCoordenador() {
		if (isCoordenador == null) {
			isCoordenador = perfis.stream().anyMatch(perfil -> perfil == Perfil.COORDENADOR.getId());
		}

		return isCoordenador;
	}

	public Boolean getIsProfessor() {
		if (isProfessor == null) {
			isProfessor = perfis.stream().anyMatch(perfil -> perfil == Perfil.PROFESSOR.getId());
		}

		return isProfessor;
	}
}