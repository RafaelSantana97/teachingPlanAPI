package edu.planner.service;

import edu.planner.models.User;
import edu.planner.repositories.IUserRepo;
import edu.planner.security.UserSS;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepo iUserRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User cli = Optional.ofNullable(iUserRepo.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserSS(cli.getId(), cli.getEmail(), cli.getHashKey(), cli.getProfiles());
    }
}