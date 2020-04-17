package edu.planner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import edu.planner.models.User;
import edu.planner.repositories.IUserRepo;
import edu.planner.security.UserSS;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepo iUserRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User cli = iUserRepo.findByEmail(email);
        if (cli == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(cli.getId(), cli.getEmail(), cli.getHashKey(), cli.getProfiles());
    }
}