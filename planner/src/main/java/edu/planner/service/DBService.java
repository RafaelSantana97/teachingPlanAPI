package edu.planner.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.planner.enums.Perfil;
import edu.planner.enums.Titulacao;
import edu.planner.models.Dominio;
import edu.planner.models.Usuario;
import edu.planner.repositories.IDominioRepo;
import edu.planner.repositories.IUsuarioRepo;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IDominioRepo iDominioRepo;

	@Autowired
	private IUsuarioRepo iUsuarioRepo;

	public void instantiateTestDatabase() throws ParseException {

		Dominio dom1 = new Dominio();
		dom1.setDominio("Tipo Disciplina");
		dom1.setAbreviacao("T");
		dom1.setValor1("Teórica");
		
		Dominio dom2 = new Dominio();
		dom2.setDominio("Tipo Disciplina");
		dom2.setAbreviacao("L");
		dom2.setValor1("Prática");
		
		iDominioRepo.saveAll(Arrays.asList(dom1, dom2));
		
		Usuario user1 = new Usuario();
		user1.setNome("Jair Rodrigo");
		user1.setEmail("rodrigoes@outlook.com");
		user1.setTitulacao(Titulacao.MESTRE);
		user1.setHashKey(bCryptPasswordEncoder.encode("1234"));
		user1.addPerfil(Perfil.ADMIN);
		user1.addPerfil(Perfil.COORDENADOR);

		Usuario user2 = new Usuario();
		user2.setNome("Vitor Silva");
		user2.setEmail("vitao@outlook.com");
		user2.setTitulacao(Titulacao.DOUTOR);
		user2.setHashKey(bCryptPasswordEncoder.encode("hueBR"));
		user2.addPerfil(Perfil.PROFESSOR);

		iUsuarioRepo.saveAll(Arrays.asList(user1, user2));
	}
}