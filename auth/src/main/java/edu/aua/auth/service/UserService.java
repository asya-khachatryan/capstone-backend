package edu.aua.auth.service;


import edu.aua.auth.dto.UserDTO;
import edu.aua.auth.persistance.User;

import java.util.List;

public interface UserService {

    User register(UserDTO userDTO);

    User update(String username, UserDTO userDTO);

    User findByUsername(String name);

    User findById(Long id);

    List<User> getAll();

    boolean delete(Long id);
}
