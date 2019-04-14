package edu.planner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.Perfil;
import edu.planner.enums.Titulacao;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String nome;

	private Integer titulacao;

	@NotEmpty
	private String email;

	@JsonIgnore
	@NotEmpty
	private String hashKey;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "USUARIO_TIPO_USUARIO", joinColumns = @JoinColumn(name = "tipoUsuario"), inverseJoinColumns = @JoinColumn(name = "id"))
	private List<TipoUsuario> tiposUsuarios = new ArrayList<TipoUsuario>();

	@Transient
	private Boolean isAdmin;

	@Transient
	private Boolean isCoordenador;

	@Transient
	private Boolean isProfessor;

	@Transient
	private Map<String, Integer> privilegios = new HashMap<String, Integer>();

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

	public Titulacao getTitulacao() {
		return Titulacao.toEnum(titulacao);
	}

	public void setTitulacao(Titulacao titulacao) {
		this.titulacao = titulacao.getId();
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

	public List<TipoUsuario> getTiposUsuarios() {
		return tiposUsuarios;
	}

	public void setTiposUsuarios(List<TipoUsuario> tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
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

	public Map<String, Integer> getPrivilegios() {
		for (TipoUsuario tipo : this.tiposUsuarios) {

			tipo.getPrivilegios().stream().forEach(priv -> this.privilegios.put(priv.getNome(), priv.getId()));
		}

		return this.privilegios;
	}
}