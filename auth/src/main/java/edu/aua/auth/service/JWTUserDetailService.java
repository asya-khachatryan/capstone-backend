package edu.aua.auth.service;


import edu.aua.auth.persistance.User;
import edu.aua.auth.service.user.model.JWTUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JWTUserDetailService implements UserDetailsService {
    private final UserService userService;

    public JWTUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.userService.findByUsernameOrThrow(username);
        log.debug("In loadUserByUsername  - user: with username: {} successfully loaded by ", username);
        return JWTUserFactory.create(user);
    }
}
