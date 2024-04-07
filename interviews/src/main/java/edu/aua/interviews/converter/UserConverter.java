package edu.aua.interviews.converter;

import edu.aua.interviews.persistance.entity.User;
import edu.aua.interviews.service.dto.UserDTO;

import java.util.List;

public interface UserConverter {
    List<UserDTO> bulkConvertToDTO(List<User> users);

    UserDTO convertToDTO(User user);

    List<User> bulkConvertToEntity(List<UserDTO> userDTOS);

    User convertToEntity(UserDTO userDTO);
}
