package edu.planner.config;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.planner.enums.LevelDegree;
import edu.planner.enums.Period;
import edu.planner.enums.Profile;
import edu.planner.enums.Semester;
import edu.planner.enums.SubjectType;
import edu.planner.models.Class;
import edu.planner.models.Course;
import edu.planner.models.Domain;
import edu.planner.models.Subject;
import edu.planner.models.User;
import edu.planner.repositories.IClassRepo;
import edu.planner.repositories.ICourseRepo;
import edu.planner.repositories.IDomainRepo;
import edu.planner.repositories.ISubjectRepo;
import edu.planner.repositories.IUserRepo;
import edu.planner.security.permission.IPermissionBaseRepo;
import edu.planner.security.permission.IPermissionRepo;
import edu.planner.security.permission.Permission;
import edu.planner.security.permission.PermissionBase;
import edu.planner.security.permission.PermissionType;
import edu.planner.security.permission.Resource;

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
	ICourseRepo iCourseRepo;

	@Autowired
	private IUserRepo iUserRepo;

	@Autowired
	private IPermissionRepo iPermissionRepo;

	@Autowired
	private IPermissionBaseRepo iPermissionBaseRepo;

	public void instantiateTestDatabase() throws ParseException {

		Domain dom1 = new Domain();
		dom1.setDomain("TIPO_DISCIPLINA");
		dom1.setAbbreviation("T");
		dom1.setValue1("Teórica");

		Domain dom2 = new Domain();
		dom2.setDomain("TIPO_DISCIPLINA");
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
		user1.setLevelDegree(LevelDegree.MESTRE.getId());
		user1.setHashKey(bCryptPasswordEncoder.encode("1234"));
		user1.addProfile(Profile.ADMIN);
		user1.addProfile(Profile.COORDINATOR);

		User user2 = new User();
		user2.setName("Vitor Silva");
		user2.setEmail("vitao@outlook.com");
		user2.setLevelDegree(LevelDegree.DOUTOR.getId());
		user2.setHashKey(bCryptPasswordEncoder.encode("hueBR"));
		user2.addProfile(Profile.TEACHER);

		User user3 = new User();
		user3.setName("André Breda");
		user3.setEmail("breda@facens.com");
		user3.setLevelDegree(LevelDegree.MESTRE.getId());
		user3.setHashKey(bCryptPasswordEncoder.encode("bredinha123"));
		user3.addProfile(Profile.TEACHER);

		User user4 = new User();
		user4.setName("Andréa Braga");
		user4.setEmail("andrea@facens.com");
		user4.setLevelDegree(LevelDegree.DOUTOR.getId());
		user4.setHashKey(bCryptPasswordEncoder.encode("andreazita"));
		user4.addProfile(Profile.ADMIN);
		user4.addProfile(Profile.COORDINATOR);
		user4.addProfile(Profile.TEACHER);

		User user5 = new User();
		user5.setName("Marcos Vinícius");
		user5.setEmail("marcosvinicius@facens.com");
		user5.setLevelDegree(LevelDegree.DOUTOR.getId());
		user5.setHashKey(bCryptPasswordEncoder.encode("relampago_marquinhos"));
		user5.addProfile(Profile.TEACHER);

		iUserRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

		Subject disp1 = new Subject();
		disp1.setName("Processamento de Sinais");
		disp1.setResponsible(user2);
		disp1.setType(SubjectType.TEORIA);

		Subject disp2 = new Subject();
		disp2.setName("Processamento de Sinais");
		disp2.setResponsible(user2);
		disp2.setType(SubjectType.LABORATORIO);

		Subject disp3 = new Subject();
		disp3.setName("Física Experimental");
		disp3.setResponsible(user2);
		disp3.setType(SubjectType.LABORATORIO);

		Subject disp4 = new Subject();
		disp4.setName("Química Experimental");
		disp4.setResponsible(user2);
		disp4.setType(SubjectType.LABORATORIO);

		Subject disp5 = new Subject();
		disp5.setName("Mecânica de Fluídos");
		disp5.setResponsible(user2);
		disp5.setType(SubjectType.LABORATORIO);

		Subject disp6 = new Subject();
		disp6.setName("Ensaio de Materiais");
		disp6.setResponsible(user2);
		disp6.setType(SubjectType.LABORATORIO);

		Subject disp7 = new Subject();
		disp7.setName("Cálculo I");
		disp7.setResponsible(user5);
		disp7.setType(SubjectType.TEORIA);

		Subject disp8 = new Subject();
		disp8.setName("Cálculo II");
		disp8.setResponsible(user5);
		disp8.setType(SubjectType.TEORIA);

		Subject disp9 = new Subject();
		disp9.setName("Cálculo III");
		disp9.setResponsible(user5);
		disp9.setType(SubjectType.TEORIA);

		Subject disp10 = new Subject();
		disp10.setName("Cálculo IV");
		disp10.setResponsible(user5);
		disp10.setType(SubjectType.TEORIA);

		Subject disp11 = new Subject();
		disp11.setName("Processamento de Sinais");
		disp11.setResponsible(user2);
		disp11.setType(SubjectType.LABORATORIO);

		Subject disp12 = new Subject();
		disp12.setName("Desenvolvimento de Aplicações Web I");
		disp12.setResponsible(user3);
		disp12.setType(SubjectType.LABORATORIO);

		iSubjectRepo.saveAll(
				Arrays.asList(disp1, disp2, disp3, disp4, disp5, disp6, disp7, disp8, disp9, disp10, disp11, disp12));

		Class t1 = new Class();
		t1.setCode("PS1TIN1");
		t1.setYear((short) 2019);
		t1.setSubject(disp1);
		t1.setPeriod(Period.MATUTINO.getId());
		t1.setSemester(Semester.SEMESTRE_1.getId());
		t1.setTeacher(user2);

		Class t2 = new Class();
		t2.setCode("LS1PIN1");
		t2.setYear((short) 2019);
		t2.setSubject(disp2);
		t2.setPeriod(Period.MATUTINO.getId());
		t2.setSemester(Semester.SEMESTRE_1.getId());
		t2.setTeacher(user2);

		iClasseRepo.saveAll(Arrays.asList(t1, t2));

		Course co1 = new Course();
		co1.setCoordinators(Arrays.asList(user4));
		co1.setName("Fundamentos do Cálculo");
		co1.setSubjects(Arrays.asList(disp7, disp8, disp9, disp10));

		iCourseRepo.saveAll(Arrays.asList(co1));

		Permission p1 = new Permission();
		p1.setResource(Resource.CLASS.getId());
		p1.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ));

		Permission p2 = new Permission();
		p2.setResource(Resource.COURSE.getId());
		p2.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ));

		Permission p3 = new Permission();
		p3.setResource(Resource.SUBJECT.getId());
		p3.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ));

		Permission p4 = new Permission();
		p4.setResource(Resource.USER.getId());
		p4.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ));

		iPermissionRepo.saveAll(Arrays.asList(p1, p2, p3, p4));

		PermissionBase admin = new PermissionBase();
		admin.setProfile(Profile.ADMIN.getId());
		admin.getPermissions().addAll(Arrays.asList(p1, p2, p3, p4));

		Permission p10 = new Permission();
		p10.setResource(Resource.CLASS.getId());
		p10.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
				PermissionType.UPDATE, PermissionType.DELETE));

		Permission p11 = new Permission();
		p11.setResource(Resource.COURSE.getId());
		p11.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
				PermissionType.UPDATE, PermissionType.DELETE));

		Permission p12 = new Permission();
		p12.setResource(Resource.SUBJECT.getId());
		p12.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
				PermissionType.UPDATE, PermissionType.DELETE));

		Permission p13 = new Permission();
		p13.setResource(Resource.USER.getId());
		p13.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
				PermissionType.UPDATE, PermissionType.DELETE));

		iPermissionRepo.saveAll(Arrays.asList(p10, p11, p12, p13));

		PermissionBase coordinator = new PermissionBase();
		coordinator.setProfile(Profile.COORDINATOR.getId());
		coordinator.getPermissions().addAll(Arrays.asList(p10, p11, p12, p13));

		Permission p20 = new Permission();
		p20.setResource(Resource.CLASS.getId());
		p20.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.CREATE));

		Permission p21 = new Permission();
		p21.setResource(Resource.COURSE.getId());
		p21.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.CREATE));

		Permission p22 = new Permission();
		p22.setResource(Resource.SUBJECT.getId());
		p22.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.CREATE));

		Permission p23 = new Permission();
		p23.setResource(Resource.USER.getId());
		p23.getPermissionTypes().addAll(Arrays.asList(PermissionType.LIST, PermissionType.CREATE));

		iPermissionRepo.saveAll(Arrays.asList(p20, p21, p22, p23));

		PermissionBase teacher = new PermissionBase();
		teacher.setProfile(Profile.TEACHER.getId());
		teacher.getPermissions().addAll(Arrays.asList(p20, p21, p22, p23));

		iPermissionBaseRepo.saveAll(Arrays.asList(admin, coordinator, teacher));
	}
}