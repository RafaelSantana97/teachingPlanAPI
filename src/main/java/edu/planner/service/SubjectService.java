package edu.planner.service;

import edu.planner.dto.SubjectDTO;
import edu.planner.dto.SubjectInsertDTO;
import edu.planner.dto.mapper.SubjectMapper;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.ObjectNotFoundException;
import edu.planner.interfaces.IService;
import edu.planner.models.Subject;
import edu.planner.repositories.ISubjectRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService implements IService<Subject, SubjectInsertDTO> {

    private final ISubjectRepo iSubjectRepo;

    @Override
    @Transactional
    public Subject insert(SubjectInsertDTO subject) {
        try {
            return iSubjectRepo.save(SubjectMapper.from(subject));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SAVE, e);
        }
    }

    @Override
    @Transactional
    public Subject update(SubjectInsertDTO subject) {
        try {
            return iSubjectRepo.save(SubjectMapper.from(subject));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_UPDATE, e);
        }
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        try {
            iSubjectRepo.delete(findOne(id));
        } catch (ConstraintViolationException e) {
            throw new BusinessException(ErrorCode.SUBJECT_DELETE_VIOLATION, e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_DELETE, e);
        }
    }

    public Page<Subject> findPageableAndFiltered(int page, int count, String description) {
        try {
            return iSubjectRepo.findByNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }
    }

    public Page<Subject> findPageable(int page, int count) {
        try {
            return iSubjectRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }
    }

    public Iterable<Subject> findAll() {
        try {
            return iSubjectRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }
    }

    public Iterable<SubjectDTO> findByCourse(final Long courseId) {
        List<SubjectDTO> subjectsDTO = new ArrayList<>();

        try {
            List<Subject> subjects = (ArrayList<Subject>) iSubjectRepo.findAll();

            if (isCourseIdValid(courseId)) {
                List<Subject> subjectsChecked = (ArrayList<Subject>) iSubjectRepo.findByCoursesIdIs(courseId);
                subjects.removeAll(subjectsChecked);

                subjectsDTO.addAll(getSubjectsToCheckedDTO(subjectsChecked, true));
            }

            subjectsDTO.addAll(getSubjectsToCheckedDTO(subjects, false));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subjectsDTO;
    }

    private boolean isCourseIdValid(final Long courseId) {
        return courseId != null && courseId > 0L;
    }

    private List<SubjectDTO> getSubjectsToCheckedDTO(final List<Subject> subjectsChecked, boolean checked) {
        return subjectsChecked.stream()
                .map(sub -> SubjectMapper.to(sub, checked))
                .collect(Collectors.toList());
    }

    public Subject findOne(final Long id) {
        Optional<Subject> subject;
        try {
            subject = iSubjectRepo.findById(id);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subject.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.SUBJECT_NOT_FOUND));
    }
}