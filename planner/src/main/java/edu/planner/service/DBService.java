package edu.planner.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.planner.enums.Perfil;
import edu.planner.enums.Periodo;
import edu.planner.enums.Semestre;
import edu.planner.enums.TipoDisciplina;
import edu.planner.enums.Titulacao;
import edu.planner.models.Disciplina;
import edu.planner.models.Dominio;
import edu.planner.models.Turma;
import edu.planner.models.Usuario;
import edu.planner.repositories.IDisciplinaRepo;
import edu.planner.repositories.IDominioRepo;
import edu.planner.repositories.ITurmaRepo;
import edu.planner.repositories.IUsuarioRepo;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IDominioRepo iDominioRepo;

	@Autowired
	private IDisciplinaRepo iDisciplinaRepo;

	@Autowired
	ITurmaRepo iTurmaRepo;

	@Autowired
	private IUsuarioRepo iUsuarioRepo;

	public void instantiateTestDatabase() throws ParseException {

		Dominio dom1 = new Dominio();
		dom1.setDominio("TIPO_DISCIPLINA");
		dom1.setAbreviacao("T");
		dom1.setValor1("Teórica");

		Dominio dom2 = new Dominio();
		dom2.setDominio("TIPO_DISCIPLINA");
		dom2.setAbreviacao("L");
		dom2.setValor1("Prática");

		Dominio dom3 = new Dominio();
		dom3.setDominio("SEMESTRE");
		dom3.setAbreviacao("S1");
		dom3.setValor1("1º Semestre");

		Dominio dom4 = new Dominio();
		dom4.setDominio("SEMESTRE");
		dom4.setAbreviacao("S2");
		dom4.setValor1("2º Semestre");

		Dominio dom5 = new Dominio();
		dom5.setDominio("PERIODO");
		dom5.setAbreviacao("1");
		dom5.setValor1("Matutino");

		Dominio dom6 = new Dominio();
		dom6.setDominio("PERIODO");
		dom6.setAbreviacao("2");
		dom6.setValor1("Vespertino");

		Dominio dom7 = new Dominio();
		dom7.setDominio("PERIODO");
		dom7.setAbreviacao("3");
		dom7.setValor1("Noturno");

		Dominio dom8 = new Dominio();
		dom8.setDominio("TITULACAO");
		dom8.setAbreviacao("");
		dom8.setValor1("Nenhum");

		Dominio dom9 = new Dominio();
		dom9.setDominio("TITULACAO");
		dom9.setAbreviacao("Esp.");
		dom9.setValor1("Especialista");

		Dominio dom10 = new Dominio();
		dom10.setDominio("TITULACAO");
		dom10.setAbreviacao("Me.");
		dom10.setValor1("Mestre");

		Dominio dom11 = new Dominio();
		dom11.setDominio("TITULACAO");
		dom11.setAbreviacao("Dr.");
		dom11.setValor1("Doutor");

		iDominioRepo.saveAll(Arrays.asList(dom1, dom2, dom3, dom4, dom5, dom6, dom7, dom8, dom9, dom10, dom11));

		Usuario user1 = new Usuario();
		user1.setNome("Jair Rodrigo");
		user1.setEmail("rodrigoes@outlook.com");
		user1.setTitulacao(Titulacao.MESTRE.getId());
		user1.setHashKey(bCryptPasswordEncoder.encode("1234"));
		user1.addPerfil(Perfil.ADMIN);
		user1.addPerfil(Perfil.COORDENADOR);

		Usuario user2 = new Usuario();
		user2.setNome("Vitor Silva");
		user2.setEmail("vitao@outlook.com");
		user2.setTitulacao(Titulacao.DOUTOR.getId());
		user2.setHashKey(bCryptPasswordEncoder.encode("hueBR"));
		user2.addPerfil(Perfil.PROFESSOR);

		iUsuarioRepo.saveAll(Arrays.asList(user1, user2));

		Disciplina disp1 = new Disciplina();
		disp1.setNome("Processamento de Sinais");
		disp1.setResponsavel(user2);
		disp1.setTipo(TipoDisciplina.TEORIA);

		Disciplina disp2 = new Disciplina();
		disp2.setNome("Processamento de Sinais");
		disp2.setResponsavel(user2);
		disp2.setTipo(TipoDisciplina.LABORATORIO);

		iDisciplinaRepo.saveAll(Arrays.asList(disp1, disp2));

		Turma t1 = new Turma();
		t1.setCodigo("PS1TIN1");
		t1.setAno(2019);
		t1.setDisciplina(disp1);
		t1.setPeriodo(Periodo.MATUTINO.getId());
		t1.setSemestre(Semestre.SEMESTRE_1.getId());
		t1.setProfessor(user2);

		Turma t2 = new Turma();
		t2.setCodigo("LS1PIN1");
		t2.setAno(2019);
		t2.setDisciplina(disp2);
		t2.setPeriodo(Periodo.MATUTINO.getId());
		t2.setSemestre(Semestre.SEMESTRE_1.getId());
		t2.setProfessor(user2);

		iTurmaRepo.saveAll(Arrays.asList(t1, t2));
	}
}