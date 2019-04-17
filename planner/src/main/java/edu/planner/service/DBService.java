package edu.planner.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.planner.enums.Perfil;
import edu.planner.enums.Period;
import edu.planner.enums.Semester;
import edu.planner.enums.SubjectType;
import edu.planner.enums.Title;
import edu.planner.models.Subject;
import edu.planner.models.Domain;
import edu.planner.models.Class;
import edu.planner.models.User;
import edu.planner.repositories.ISubjectRepo;
import edu.planner.repositories.IDomainRepo;
import edu.planner.repositories.IClassRepo;
import edu.planner.repositories.IUserRepo;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IDomainRepo iDomainRepo;

	@Autowired
	private ISubjectRepo iSubjectRepo;

	@Autowired
	IClassRepo iClasseRepo;

	@Autowired
	private IUserRepo iUserRepo;

	public void instantiateTestDatabase() throws ParseException {

		Domain dom1 = new Domain();
		dom1.setDomain("TIPO_SUBJECT");
		dom1.setAbbreviation("T");
		dom1.setValue1("Teórica");

		Domain dom2 = new Domain();
		dom2.setDomain("TIPO_SUBJECT");
		dom2.setAbbreviation("L");
		dom2.setValue1("Prática");

		Domain dom3 = new Domain();
		dom3.setDomain("SEMESTRE");
		dom3.setAbbreviation("S1");
		dom3.setValue1("1º Semester");

		Domain dom4 = new Domain();
		dom4.setDomain("SEMESTRE");
		dom4.setAbbreviation("S2");
		dom4.setValue1("2º Semester");

		Domain dom5 = new Domain();
		dom5.setDomain("PERIODO");
		dom5.setAbbreviation("1");
		dom5.setValue1("Matutino");

		Domain dom6 = new Domain();
		dom6.setDomain("PERIODO");
		dom6.setAbbreviation("2");
		dom6.setValue1("Vespertino");

		Domain dom7 = new Domain();
		dom7.setDomain("PERIODO");
		dom7.setAbbreviation("3");
		dom7.setValue1("Noturno");

		Domain dom8 = new Domain();
		dom8.setDomain("TITULACAO");
		dom8.setAbbreviation("");
		dom8.setValue1("Nenhum");

		Domain dom9 = new Domain();
		dom9.setDomain("TITULACAO");
		dom9.setAbbreviation("Esp.");
		dom9.setValue1("Especialista");

		Domain dom10 = new Domain();
		dom10.setDomain("TITULACAO");
		dom10.setAbbreviation("Me.");
		dom10.setValue1("Mestre");

		Domain dom11 = new Domain();
		dom11.setDomain("TITULACAO");
		dom11.setAbbreviation("Dr.");
		dom11.setValue1("Doutor");

		iDomainRepo.saveAll(Arrays.asList(dom1, dom2, dom3, dom4, dom5, dom6, dom7, dom8, dom9, dom10, dom11));

		User user1 = new User();
		user1.setName("Jair Rodrigo");
		user1.setEmail("rodrigoes@outlook.com");
		user1.setTitulacao(Title.MESTRE.getId());
		user1.setHashKey(bCryptPasswordEncoder.encode("1234"));
		user1.addPerfil(Perfil.ADMIN);
		user1.addPerfil(Perfil.COORDINATOR);

		User user2 = new User();
		user2.setName("Vitor Silva");
		user2.setEmail("vitao@outlook.com");
		user2.setTitulacao(Title.DOUTOR.getId());
		user2.setHashKey(bCryptPasswordEncoder.encode("hueBR"));
		user2.addPerfil(Perfil.TEACHER);

		iUserRepo.saveAll(Arrays.asList(user1, user2));

		Subject disp1 = new Subject();
		disp1.setName("Processamento de Sinais");
		disp1.setResponsible(user2);
		disp1.setType(SubjectType.TEORIA);

		Subject disp2 = new Subject();
		disp2.setName("Processamento de Sinais");
		disp2.setResponsible(user2);
		disp2.setType(SubjectType.LABORATORIO);

		iSubjectRepo.saveAll(Arrays.asList(disp1, disp2));

		Class t1 = new Class();
		t1.setCode("PS1TIN1");
		t1.setYear(2019);
		t1.setSubject(disp1);
		t1.setPeriod(Period.MATUTINO.getId());
		t1.setSemester(Semester.SEMESTRE_1.getId());
		t1.setTeacher(user2);

		Class t2 = new Class();
		t2.setCode("LS1PIN1");
		t2.setYear(2019);
		t2.setSubject(disp2);
		t2.setPeriod(Period.MATUTINO.getId());
		t2.setSemester(Semester.SEMESTRE_1.getId());
		t2.setTeacher(user2);

		iClasseRepo.saveAll(Arrays.asList(t1, t2));
	}
}