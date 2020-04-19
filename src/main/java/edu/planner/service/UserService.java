package edu.planner.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import edu.planner.dto.UserSimpleDTO;
import edu.planner.exception.AuthorizationException;
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

@Service
@RequiredArgsConstructor
public class UserService implements IService<User, UserInsertDTO> {

    private final IUserRepo iUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User insert(UserInsertDTO user) {
        try {
            User userIncluded = UserInsertDTO.fromDTO(user);
            userIncluded.setHashKey(bCryptPasswordEncoder.encode(userIncluded.getHashKey()));

            if (isFirstAdminRequest(user)) {
                userIncluded.setProfiles(userIncluded.getRequiredProfilesShort());
                userIncluded.setRequiredProfiles(new HashSet<>());
            }

            return iUserRepo.save(userIncluded);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SAVE, e);
        }
    }

    private boolean isFirstAdminRequest(UserInsertDTO user) {
        return user.getRequestedRoles().contains(Profile.ADMIN) && !iUserRepo.existsSomeAdminUser();
    }

    @Override
    @Transactional
    public User update(UserInsertDTO user) {
        try {
            return iUserRepo.save(UserInsertDTO.fromDTO(user));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_UPDATE, e);
        }
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
        try {
            return iUserRepo.findByNameContaining(PageRequest.of(page, count), description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }
    }

    public Page<User> findPageable(int page, int count) {
        try {
            return iUserRepo.findAll(PageRequest.of(page, count));
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }
    }

    public Page<User> findPageableAndFilteredProfile(int page, int count, Short profile, String description) {
        try {
            return iUserRepo.findByProfilesInAndNameContaining(PageRequest.of(page, count), profile, description);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }
    }

    public Page<User> findPageableByProfile(int page, int count, Short profile) {
        try {
            return iUserRepo.findByProfilesIs(PageRequest.of(page, count), profile);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }
    }

    public Iterable<User> findAll() {
        try {
            return iUserRepo.findAll();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }
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

    public UserSimpleDTO getSimpleUser() {
        UserSS userSS = Optional.ofNullable(UserService.authenticated())
                .orElseThrow(() -> new AuthorizationException("Access denied"));

        return UserSimpleDTO.toDTO(this.findOne(userSS.getId()));
    }

    public User getSpecificUser(Long id) {
        UserSS userSS = UserService.authenticated();
        if (isNotProfileOwnerNorAdmin(userSS, id)) {
            throw new AuthorizationException("Access denied");
        }

        return findOne(id);
    }

    private boolean isNotProfileOwnerNorAdmin(UserSS userSS, Long id) {
        return userSS == null || !userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId());
    }

    public Page<UserPermissionsDTO> findAllRequiredPermissionsUsers(int page, int count) {
        try {
            Page<User> users = iUserRepo.findDistinctByRequiredProfilesNotNull(PageRequest.of(page, count));

            int totalElements = (int) users.getTotalElements();
            return new PageImpl<>(
                    users.stream().map(UserPermissionsDTO::toDTO).collect(Collectors.toList()),
                    PageRequest.of(page, count), totalElements);

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SEARCH, e);
        }
    }

    public UserPermissionsDTO grantPermissionTo(UserPermissionsDTO user) {
        try {
            User userFromDB = findOne(user.getId());
            userFromDB.setProfiles(Profile.profilesToShorts(user.getRequestedRoles()));

            userFromDB = iUserRepo.save(userFromDB);

            return UserPermissionsDTO.toDTO(userFromDB);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.USER_SAVE, e);
        }
    }
}