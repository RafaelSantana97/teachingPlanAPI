package edu.planner.dto.mapper;

import edu.planner.dto.SubjectDTO;
import edu.planner.dto.SubjectInsertDTO;
import edu.planner.models.Subject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectMapper {

    public static SubjectDTO to(Subject subject, boolean checked) {
        return new SubjectDTO(subject.getId(), subject.getName(), subject.getType(),
                UserMapper.toSimpleDTO(subject.getResponsible()), checked);
    }

    public static Subject from(SubjectDTO subject) {
        return new Subject(subject.getId(), subject.getName(), subject.getType(),
                UserMapper.from(subject.getResponsible()));
    }

    public static Subject from(SubjectInsertDTO subject) {
        return new Subject(subject.getId(), subject.getName(), subject.getType(),
                UserMapper.from(subject.getResponsible()));
    }
}
