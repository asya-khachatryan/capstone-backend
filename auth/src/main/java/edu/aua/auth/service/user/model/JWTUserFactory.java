package edu.aua.auth.service.user.model;

import edu.aua.auth.persistance.Role;
import edu.aua.auth.persistance.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JWTUserFactory {

    private JWTUserFactory() {
    }

    public static JWTUser create(User user) {
        return new JWTUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrandAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrandAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(o -> new SimpleGrantedAuthority(o.getName().name()))
                .collect(Collectors.toList());
    }
}
