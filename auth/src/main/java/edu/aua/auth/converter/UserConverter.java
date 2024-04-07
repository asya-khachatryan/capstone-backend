package edu.aua.auth.converter;


import edu.aua.auth.dto.UserDTO;
import edu.aua.auth.persistance.User;

import java.util.List;

public interface UserConverter {

    User convert(UserDTO userDTO);

    UserDTO convert(User user);

    List<UserDTO> bulkConvert(List<User> users);
}
