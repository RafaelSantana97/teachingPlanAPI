package edu.planner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.planner.dto.SubjectDTO;
import edu.planner.dto.SubjectInsertDTO;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.exception.ObjectNotFoundException;
import edu.planner.interfaces.IService;
import edu.planner.models.Subject;
import edu.planner.repositories.ISubjectRepo;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SubjectService implements IService<Subject, SubjectInsertDTO> {

    private final ISubjectRepo iSubjectRepo;

    @Override
    @Transactional
    public Subject insert(SubjectInsertDTO subject) {
        Subject subjectIncluded;
        try {
            subjectIncluded = iSubjectRepo.save(SubjectInsertDTO.fromDTO(subject));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SAVE, e);
        }
        return subjectIncluded;
    }

    @Override
    @Transactional
    public Subject update(SubjectInsertDTO subject) {
        Subject subjectAltered;
        try {
            subjectAltered = iSubjectRepo.save(SubjectInsertDTO.fromDTO(subject));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_UPDATE, e);
        }
        return subjectAltered;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            iSubjectRepo.delete(findOne(id));
        } catch (ConstraintViolationException e) {
            throw new BusinessException(ErrorCode.SUBJECT_DELETE_VIOLATION, e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_DELETE, e);
        }
    }

    public Page<Subject> findPageableAndFiltered(int page, int count, String description) {
        Page<Subject> subject;
        try {
            subject = iSubjectRepo.findByNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subject;
    }

    public Page<Subject> findPageable(int page, int count) {
        Page<Subject> subject;
        try {
            subject = iSubjectRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subject;
    }

    public Iterable<Subject> findAll() {
        Iterable<Subject> subject;
        try {
            subject = iSubjectRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subject;
    }

    public Iterable<SubjectDTO> findByCourse(Long courseId) {
        List<Subject> subjects;
        List<SubjectDTO> subjectsDTO = new ArrayList<>();

        try {
            subjects = (ArrayList<Subject>) iSubjectRepo.findAll();

            if (courseId != null && courseId > 0) {
                List<Subject> subjectsChecked = (ArrayList<Subject>) iSubjectRepo.findByCoursesIdIs(courseId);

                subjectsChecked.forEach(sub -> subjectsDTO.add(SubjectDTO.toDTO(sub, true)));

                subjects.removeAll(subjectsChecked);
            }

            subjects.forEach(sub -> subjectsDTO.add(SubjectDTO.toDTO(sub, false)));

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subjectsDTO;
    }

    public Subject findOne(Long id) {
        Optional<Subject> subject;
        try {
            subject = iSubjectRepo.findById(id);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SUBJECT_SEARCH, e);
        }

        return subject.orElseThrow(() -> new ObjectNotFoundException(ErrorCode.SUBJECT_NOT_FOUND));
    }
}