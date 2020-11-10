package edu.planner.controllers;

import edu.planner.dto.UserInsertDTO;
import edu.planner.dto.UserPermissionsDTO;
import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.Profile;
import edu.planner.models.User;
import edu.planner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> insert(@Valid @RequestBody UserInsertDTO user) {
        User userIncluded = userService.insert(user);
        return userIncluded != null ? ResponseEntity.ok(userIncluded) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody UserInsertDTO user) {
        User userAltered = userService.update(user);
        return userAltered != null ? ResponseEntity.ok(userAltered) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(true);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}/{description}")
    public ResponseEntity<Page<User>> findPageableAndFiltered(@PathVariable("page") int page,
                                                              @PathVariable("count") int count, @PathVariable("description") String description) {
        Page<User> user = userService.findPageableAndFiltered(page, count, description);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}")
    public ResponseEntity<Page<User>> findPageable(@PathVariable("page") int page, @PathVariable("count") int count) {
        Page<User> user = userService.findPageable(page, count);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}/teacher/{description}")
    public ResponseEntity<Page<User>> findPageableAndFilteredTeacher(@PathVariable("page") int page,
                                                                     @PathVariable("count") int count, @PathVariable("description") String description) {
        Page<User> user = userService.findPageableAndFilteredProfile(page, count, Profile.TEACHER.getId(), description);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}/teacher")
    public ResponseEntity<Page<User>> findPageableByTeacher(@PathVariable("page") int page,
                                                            @PathVariable("count") int count) {
        Page<User> user = userService.findPageableByProfile(page, count, Profile.TEACHER.getId());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}/coordinator/{description}")
    public ResponseEntity<Page<User>> findPageableAndFilteredCoord(@PathVariable("page") int page,
                                                                   @PathVariable("count") int count, @PathVariable("description") String description) {
        Page<User> user = userService.findPageableAndFilteredProfile(page, count, Profile.COORDINATOR.getId(),
                description);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}/coordinator")
    public ResponseEntity<Page<User>> findPageableByCoord(@PathVariable("page") int page,
                                                          @PathVariable("count") int count) {
        Page<User> user = userService.findPageableByProfile(page, count, Profile.COORDINATOR.getId());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> findAll() {
        Iterable<User> user = userService.findAll();
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") Long id) {
        User user = userService.getSpecificUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @GetMapping("getSimpleUser")
    public ResponseEntity<UserSimpleDTO> getSimpleUser() {
        return ResponseEntity.ok(userService.getSimpleUser());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interval/{page}/{count}/requiredPermissionsUsers")
    public ResponseEntity<Page<UserPermissionsDTO>> findAllRequiredPermissionsUsers(@PathVariable("page") int page,
                                                                                    @PathVariable("count") int count) {
        Page<UserPermissionsDTO> user = userService.findAllRequiredPermissionsUsers(page, count);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/grantAllRequiredPermissions")
    public ResponseEntity<UserPermissionsDTO> grantPermissionTo(@RequestBody UserPermissionsDTO user) {
        UserPermissionsDTO userGranted = userService.grantPermissionTo(user);
        return userGranted != null ? ResponseEntity.ok(userGranted) : ResponseEntity.noContent().build();
    }
}