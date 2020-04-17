package edu.planner.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.planner.dto.UserInsertDTO;
import edu.planner.dto.UserPermissionsDTO;
import edu.planner.enums.Profile;
import edu.planner.exception.BusinessException;
import edu.planner.exception.ErrorCode;
import edu.planner.interfaces.IService;
import edu.planner.models.User;
import edu.planner.repositories.IUserRepo;
import edu.planner.security.UserSS;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IService<User, UserInsertDTO> {

    private final IUserRepo iUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User insert(UserInsertDTO user) {
        User userIncluded;
        try {
            userIncluded = UserInsertDTO.fromDTO(user);
            userIncluded.setHashKey(bCryptPasswordEncoder.encode(userIncluded.getHashKey()));

            if (user.getRequireAdminRole() && !iUserRepo.existsSomeAdminUser()) {
                userIncluded.setProfiles(userIncluded.getRequiredProfilesShort());
                userIncluded.setRequiredProfiles(new HashSet<>());
            }

            userIncluded = iUserRepo.save(userIncluded);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SAVE, e);
        }
        return userIncluded;
    }

    @Override
    @Transactional
    public User update(UserInsertDTO user) {
        User userAltered;
        try {
            userAltered = iUserRepo.save(UserInsertDTO.fromDTO(user));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_UPDATE, e);
        }
        return userAltered;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            iUserRepo.deleteById(id);
        } catch (ConstraintViolationException e) {
            throw new BusinessException(ErrorCode.USER_DELETE_VIOLATION, e);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_DELETE, e);
        }
    }

    public Page<User> findPageableAndFiltered(int page, int count, String description) {
        Page<User> user;
        try {
            user = iUserRepo.findByNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return user;
    }

    public Page<User> findPageable(int page, int count) {
        Page<User> user;
        try {
            user = iUserRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return user;
    }

    public Page<User> findPageableAndFilteredProfile(int page, int count, Short profile, String description) {
        Page<User> user;
        try {
            user = iUserRepo.findByProfilesInAndNameContaining(PageRequest.of(page, count), profile, description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return user;
    }

    public Page<User> findPageableByProfile(int page, int count, Short profile) {
        Page<User> user;
        try {
            user = iUserRepo.findByProfilesIs(PageRequest.of(page, count), profile);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return user;
    }

    public Iterable<User> findAll() {
        Iterable<User> user;
        try {
            user = iUserRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return user;
    }

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public User findOne(Long id) {
        Optional<User> user;
        try {
            user = iUserRepo.findById(id);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return user.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    public Page<UserPermissionsDTO> findAllRequiredPermissionsUsers(int page, int count) {
        Page<User> users;
        PageImpl<UserPermissionsDTO> usersDTO;
        try {
            users = iUserRepo.findDistinctByRequiredProfilesNotNull(PageRequest.of(page, count));

            int totalElements = (int) users.getTotalElements();
            usersDTO = new PageImpl<>(
                    users.stream().map(UserPermissionsDTO::toDTO).collect(Collectors.toList()),
                    PageRequest.of(page, count), totalElements);

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }

        return usersDTO;
    }

    public UserPermissionsDTO grantPermissionTo(UserPermissionsDTO user) {
        UserPermissionsDTO userGranted;
        try {
            User userFromDB = findOne(user.getId());

            if (user.getRequiredAdminRole())
                userFromDB.addProfile(Profile.ADMIN);

            if (user.getRequiredCoordinatorRole())
                userFromDB.addProfile(Profile.COORDINATOR);

            if (user.getRequiredTeacherRole())
                userFromDB.addProfile(Profile.TEACHER);

            userFromDB = iUserRepo.save(userFromDB);

            userGranted = UserPermissionsDTO.toDTO(userFromDB);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SAVE, e);
        }
        return userGranted;
    }
}