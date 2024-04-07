package edu.aua.interviews.converter.impl;

import edu.aua.interviews.converter.InterviewConverter;
import edu.aua.interviews.converter.UserConverter;
import edu.aua.interviews.persistance.entity.User;
import edu.aua.interviews.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Autowired
    private InterviewConverter interviewConverter;

    @Override
    public List<UserDTO> bulkConvertToDTO(List<User> users) {
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public List<User> bulkConvertToEntity(List<UserDTO> userDTOS) {
        return userDTOS.stream().map(this::convertToEntity).collect(Collectors.toList());

    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        final User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
