package edu.aua.interviews.service.impl;

import edu.aua.interviews.excaption.UserNotFoundException;
import edu.aua.interviews.persistance.User;
import edu.aua.interviews.repositories.UserRepository;
import edu.aua.interviews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user found by this id", id));
    }
}

