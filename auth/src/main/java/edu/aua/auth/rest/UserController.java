package edu.aua.auth.rest;


import edu.aua.auth.converter.UserConverter;
import edu.aua.auth.dto.UserDTO;
import edu.aua.auth.dto.UserProfileDTO;
import edu.aua.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> update(@PathVariable String username, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userConverter.convert(userService.update(username, userDTO)));
    }

    @GetMapping
    public List<? extends UserDTO> getAll() {
        return userConverter.bulkConvert(userService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<UserDTO> search(@RequestParam("username") String username) {
        return ResponseEntity.ok(userConverter.convert(userService.findByUsername(username)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userConverter.convert(userService.findById(id)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userdto) {
        System.out.println(userdto);
        return ResponseEntity.ok(userConverter.convert(userService.register(userdto)));
    }

    @GetMapping("/me")
    public UserProfileDTO getUser(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        if (userDetails == null) {
            return new UserProfileDTO(true, null);
        }
        return new UserProfileDTO(false,
                userConverter.convert(userService.findByUsername(userDetails.getUsername())));
    }

}
