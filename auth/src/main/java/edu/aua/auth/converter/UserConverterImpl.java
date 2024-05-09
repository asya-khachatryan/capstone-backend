package edu.aua.auth.converter;

import edu.aua.auth.dto.UserDTO;
import edu.aua.auth.persistance.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User convert(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User entity = new User();
        entity.setId(userDTO.getId());
        entity.setFirstName(userDTO.getFirstName());
        entity.setLastName(userDTO.getLastName());
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        entity.setEmail(userDTO.getEmail());
        entity.setPhoneNumber(userDTO.getPhoneNumber());

        return entity;
    }

    @Override
    public UserDTO convert(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        return dto;
    }

    @Override
    public List<UserDTO> bulkConvert(List<User> users) {
        return users.stream().map(this::convert)
                .collect(Collectors.toList());
    }
}
