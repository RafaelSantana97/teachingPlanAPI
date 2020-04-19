package edu.planner.security;

import edu.planner.enums.Profile;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserSS implements UserDetails {
    private static final long serialVersionUID = -6408434735113646515L;

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSS(Long id, String username, String password, Set<Profile> profiles) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Profile profile) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }

    public boolean hasAnyRole(List<Profile> profiles) {
        return profiles.stream()
                .anyMatch(profile -> getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription())));
    }
}