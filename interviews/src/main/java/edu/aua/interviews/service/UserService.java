package edu.aua.interviews.service;

import edu.aua.interviews.excaption.UserNotFoundException;
import edu.aua.interviews.persistance.User;

public interface UserService {

    User findById(Long id) throws UserNotFoundException;
}
