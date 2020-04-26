package edu.planner.config;

import edu.planner.enums.*;
import edu.planner.models.Class;
import edu.planner.models.*;
import edu.planner.repositories.*;
import edu.planner.security.permission.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DBService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IDomainRepo iDomainRepo;
    private final ISubjectRepo iSubjectRepo;
    private final IClassRepo iClassRepo;
    private final ICourseRepo iCourseRepo;
    private final IUserRepo iUserRepo;
    private final IPermissionRepo iPermissionRepo;
    private final IPermissionBaseRepo iPermissionBaseRepo;

    public void instantiateEssentialData() {
        this.createDomains();
        this.createPermissions();
    }

    public void instantiateTestData() {
        List<User> users = this.createUsers();
        List<Subject> subjects = this.createSubjects(users);

        this.createClasses(users, subjects);
        this.createCourses(users, subjects);
    }

    private void createDomains() {
        Domain dom1 = new Domain();
        dom1.setDescription("TIPO_DISCIPLINA");
        dom1.setAbbreviation(SubjectType.TEORIA.getId());
        dom1.setValue1(SubjectType.TEORIA.getDescription());

        Domain dom2 = new Domain();
        dom2.setDescription("TIPO_DISCIPLINA");
        dom2.setAbbreviation(SubjectType.LABORATORIO.getId());
        dom2.setValue1(SubjectType.LABORATORIO.getDescription());

        Domain dom3 = new Domain();
        dom3.setDescription("SEMESTRE");
        dom3.setAbbreviation(Semester.SEMESTRE_1.getId());
        dom3.setValue1(Semester.SEMESTRE_1.getDescription());

        Domain dom4 = new Domain();
        dom4.setDescription("SEMESTRE");
        dom4.setAbbreviation(Semester.SEMESTRE_2.getId());
        dom4.setValue1(Semester.SEMESTRE_2.getDescription());

        Domain dom5 = new Domain();
        dom5.setDescription("PERIODO");
        dom5.setAbbreviation(Period.MATUTINO.getId().toString());
        dom5.setValue1(Period.MATUTINO.getDescription());

        Domain dom6 = new Domain();
        dom6.setDescription("PERIODO");
        dom6.setAbbreviation(Period.VERSPERTINO.getId().toString());
        dom6.setValue1(Period.VERSPERTINO.getDescription());

        Domain dom7 = new Domain();
        dom7.setDescription("PERIODO");
        dom7.setAbbreviation(Period.NOTURNO.getId().toString());
        dom7.setValue1(Period.NOTURNO.getDescription());

        Domain dom8 = new Domain();
        dom8.setDescription("TITULACAO");
        dom8.setAbbreviation(LevelDegree.NENHUM.getId());
        dom8.setValue1(LevelDegree.NENHUM.getDescription());

        Domain dom9 = new Domain();
        dom9.setDescription("TITULACAO");
        dom9.setAbbreviation(LevelDegree.ESPECIALISTA.getId());
        dom9.setValue1(LevelDegree.ESPECIALISTA.getDescription());

        Domain dom10 = new Domain();
        dom10.setDescription("TITULACAO");
        dom10.setAbbreviation(LevelDegree.MESTRE.getId());
        dom10.setValue1(LevelDegree.MESTRE.getId());

        Domain dom11 = new Domain();
        dom11.setDescription("TITULACAO");
        dom11.setAbbreviation(LevelDegree.DOUTOR.getId());
        dom11.setValue1(LevelDegree.DOUTOR.getDescription());

        iDomainRepo.saveAll(Set.of(dom1, dom2, dom3, dom4, dom5, dom6, dom7, dom8, dom9, dom10, dom11));
    }

    private List<User> createUsers() {
        User user1 = new User();
        user1.setName("Jair Rodrigo");
        user1.setEmail("rodrigoes@outlook.com");
        user1.setLevelDegree(LevelDegree.MESTRE.getId());
        user1.setHashKey(bCryptPasswordEncoder.encode("1234"));
        user1.setProfiles(Set.of(Profile.ADMIN.getId(), Profile.COORDINATOR.getId()));

        User user2 = new User();
        user2.setName("Vitor Silva");
        user2.setEmail("vitao@outlook.com");
        user2.setLevelDegree(LevelDegree.DOUTOR.getId());
        user2.setHashKey(bCryptPasswordEncoder.encode("hueBR"));
        user2.setProfiles(Set.of(Profile.TEACHER.getId()));

        User user3 = new User();
        user3.setName("André Breda");
        user3.setEmail("breda@facens.com");
        user3.setLevelDegree(LevelDegree.MESTRE.getId());
        user3.setHashKey(bCryptPasswordEncoder.encode("bredinha123"));
        user3.setProfiles(Set.of(Profile.TEACHER.getId()));

        User user4 = new User();
        user4.setName("Andréa Braga");
        user4.setEmail("andrea@facens.com");
        user4.setLevelDegree(LevelDegree.DOUTOR.getId());
        user4.setHashKey(bCryptPasswordEncoder.encode("andreazita"));
        user4.setProfiles(Set.of(Profile.ADMIN.getId(), Profile.COORDINATOR.getId(), Profile.TEACHER.getId()));

        User user5 = new User();
        user5.setName("Marcos Vinícius");
        user5.setEmail("marcosvinicius@facens.com");
        user5.setLevelDegree(LevelDegree.DOUTOR.getId());
        user5.setHashKey(bCryptPasswordEncoder.encode("relampago_marquinhos"));
        user5.setProfiles(Set.of(Profile.TEACHER.getId()));

        return (List<User>) iUserRepo.saveAll(Set.of(user1, user2, user3, user4, user5));
    }

    private List<Subject> createSubjects(List<User> users) {
        Subject subj1 = new Subject();
        subj1.setName("Processamento de Sinais");
        subj1.setResponsible(users.get(1));
        subj1.setType(SubjectType.TEORIA);

        Subject subj2 = new Subject();
        subj2.setName("Processamento de Sinais");
        subj2.setResponsible(users.get(1));
        subj2.setType(SubjectType.LABORATORIO);

        Subject subj3 = new Subject();
        subj3.setName("Física Experimental");
        subj3.setResponsible(users.get(1));
        subj3.setType(SubjectType.LABORATORIO);

        Subject subj4 = new Subject();
        subj4.setName("Química Experimental");
        subj4.setResponsible(users.get(1));
        subj4.setType(SubjectType.LABORATORIO);

        Subject subj5 = new Subject();
        subj5.setName("Mecânica de Fluídos");
        subj5.setResponsible(users.get(1));
        subj5.setType(SubjectType.LABORATORIO);

        Subject subj6 = new Subject();
        subj6.setName("Ensaio de Materiais");
        subj6.setResponsible(users.get(1));
        subj6.setType(SubjectType.LABORATORIO);

        Subject subj7 = new Subject();
        subj7.setName("Cálculo I");
        subj7.setResponsible(users.get(4));
        subj7.setType(SubjectType.TEORIA);

        Subject subj8 = new Subject();
        subj8.setName("Cálculo II");
        subj8.setResponsible(users.get(4));
        subj8.setType(SubjectType.TEORIA);

        Subject subj9 = new Subject();
        subj9.setName("Cálculo III");
        subj9.setResponsible(users.get(4));
        subj9.setType(SubjectType.TEORIA);

        Subject subj10 = new Subject();
        subj10.setName("Cálculo IV");
        subj10.setResponsible(users.get(4));
        subj10.setType(SubjectType.TEORIA);

        Subject subj11 = new Subject();
        subj11.setName("Linguagens Formais e Automatos");
        subj11.setResponsible(users.get(1));
        subj11.setType(SubjectType.LABORATORIO);

        Subject subj12 = new Subject();
        subj12.setName("Desenvolvimento de Aplicações Web I");
        subj12.setResponsible(users.get(2));
        subj12.setType(SubjectType.LABORATORIO);

        return (List<Subject>) iSubjectRepo.saveAll(
                Set.of(subj1, subj2, subj3, subj4, subj5, subj6, subj7, subj8, subj9, subj10, subj11, subj12));

    }

    private void createClasses(List<User> users, List<Subject> subjects) {
        Class t1 = new Class();
        t1.setCode("PS1TIN1");
        t1.setYear((short) 2019);
        t1.setSubject(subjects.get(0));
        t1.setPeriod(Period.MATUTINO.getId());
        t1.setSemester(Semester.SEMESTRE_1.getId());
        t1.setTeacher(users.get(1));

        Class t2 = new Class();
        t2.setCode("LS1PIN1");
        t2.setYear((short) 2019);
        t2.setSubject(subjects.get(1));
        t2.setPeriod(Period.MATUTINO.getId());
        t2.setSemester(Semester.SEMESTRE_1.getId());
        t2.setTeacher(users.get(1));

        iClassRepo.saveAll(Set.of(t1, t2));
    }

    private void createCourses(List<User> users, List<Subject> subjects) {
        Course co1 = new Course();
        co1.setCoordinators(Collections.singletonList(users.get(3)));
        co1.setName("Fundamentos do Cálculo");
        co1.setSubjects(subjects.subList(6, 10));

        iCourseRepo.saveAll(Collections.singletonList(co1));
    }

    private void createPermissions() {
        Permission p1 = new Permission();
        p1.setResource(Resource.CLASS.getId());
        p1.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ));

        Permission p2 = new Permission();
        p2.setResource(Resource.COURSE.getId());
        p2.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ));

        Permission p3 = new Permission();
        p3.setResource(Resource.SUBJECT.getId());
        p3.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ));

        Permission p4 = new Permission();
        p4.setResource(Resource.USER.getId());
        p4.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ));

        iPermissionRepo.saveAll(Set.of(p1, p2, p3, p4));

        PermissionBase admin = new PermissionBase();
        admin.setProfile(Profile.ADMIN.getId());
        admin.getPermissions().addAll(Set.of(p1, p2, p3, p4));

        Permission p10 = new Permission();
        p10.setResource(Resource.CLASS.getId());
        p10.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
                PermissionType.UPDATE, PermissionType.DELETE));

        Permission p11 = new Permission();
        p11.setResource(Resource.COURSE.getId());
        p11.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
                PermissionType.UPDATE, PermissionType.DELETE));

        Permission p12 = new Permission();
        p12.setResource(Resource.SUBJECT.getId());
        p12.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
                PermissionType.UPDATE, PermissionType.DELETE));

        Permission p13 = new Permission();
        p13.setResource(Resource.USER.getId());
        p13.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.READ, PermissionType.CREATE,
                PermissionType.UPDATE, PermissionType.DELETE));

        iPermissionRepo.saveAll(Set.of(p10, p11, p12, p13));

        PermissionBase coordinator = new PermissionBase();
        coordinator.setProfile(Profile.COORDINATOR.getId());
        coordinator.getPermissions().addAll(Set.of(p10, p11, p12, p13));

        Permission p20 = new Permission();
        p20.setResource(Resource.CLASS.getId());
        p20.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.CREATE));

        Permission p21 = new Permission();
        p21.setResource(Resource.COURSE.getId());
        p21.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.CREATE));

        Permission p22 = new Permission();
        p22.setResource(Resource.SUBJECT.getId());
        p22.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.CREATE));

        Permission p23 = new Permission();
        p23.setResource(Resource.USER.getId());
        p23.getPermissionTypes().addAll(Set.of(PermissionType.LIST, PermissionType.CREATE));

        iPermissionRepo.saveAll(Set.of(p20, p21, p22, p23));

        PermissionBase teacher = new PermissionBase();
        teacher.setProfile(Profile.TEACHER.getId());
        teacher.getPermissions().addAll(Set.of(p20, p21, p22, p23));

        iPermissionBaseRepo.saveAll(Set.of(admin, coordinator, teacher));
    }
}