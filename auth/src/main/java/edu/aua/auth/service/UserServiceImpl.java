package edu.aua.auth.service;


import edu.aua.auth.converter.UserConverter;
import edu.aua.auth.dto.UserDTO;
import edu.aua.auth.persistance.User;
import edu.aua.auth.repository.RoleRepository;
import edu.aua.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserConverter userConverter,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User update(String username, UserDTO userDTO) {
        final User user = this.userRepository.findByUsername(username);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return user;
    }

    @Override
    public User register(UserDTO userDTO) {
        log.debug("Requested to create user for email - {}", userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public User findByUsername(String username) {
        final User user = this.userRepository.findByUsername(username);

        log.debug("IN findByUsername  - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findById(Long id) {
        final User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Not found user by this id%d", id)));
        log.debug("IN findById user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        final List<User> users = this.userRepository.findAll();
        log.debug("IN getAll  : {} user found", users.size());
        return users;
    }

    @Override
    public boolean delete(Long id) {
        this.userRepository.deleteById(id);
        log.debug("IN delete user deleted by id: {}", id);
        return this.userRepository.existsById(id);
    }
}
