package com.booleanuk.api.controllers;

import com.booleanuk.api.models.*;
import com.booleanuk.api.models.DTOs.User.UserCreateDTO;
import com.booleanuk.api.models.DTOs.User.UserReadDTO;
import com.booleanuk.api.models.DTOs.User.UserReadNoProjectDTO;
import com.booleanuk.api.models.DTOs.User.UserUpdateProjectDTO;
import com.booleanuk.api.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    // ------------------ ENDPOINTS ------------------//
    //region // POST //
    @PostMapping
    @RequestMapping("users")
    public ResponseEntity<String> create(@RequestBody UserCreateDTO user) {
        User userToCreate = modelMapper.map(user, User.class);

        User newUser;
        try {
            newUser = this.userRepository.save(userToCreate);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "One or more values are missing or invalid."
            );
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{userId}")
                        .buildAndExpand(newUser.getId())
                        .toUri())
                .body("Created successfully.");
    }
    //endregion
    //region // GET //
    @GetMapping("users/{userId}")
    public ResponseEntity<UserReadDTO> get(@PathVariable int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No user with that userId was found."
                ));

        return ResponseEntity.ok(modelMapper
                .map(user, UserReadDTO.class)
        );
    }
    @GetMapping("/projects/{projectId}/users")
    public ResponseEntity<List<UserReadNoProjectDTO>> getAllFromProject(@PathVariable int projectId) {
        List<User> users = this.userRepository.findAllByProjectId(projectId);

        if(users.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No users were found with that projectId."
            );

        return ResponseEntity.ok(users
                .stream()
                .map(user -> modelMapper
                        .map(user, UserReadNoProjectDTO.class))
                .collect(Collectors.toList())
        );
    }
    //endregion
    //region // PUT //
    @PutMapping("users/{userId}/project")
    public ResponseEntity<String> updateProject(@PathVariable int userId, @RequestBody UserUpdateProjectDTO user) {
        User userToUpdate = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No user with that userId was found."
                ));

        if(!Objects.equals(userToUpdate.getProject(), user.getProject())) {
            userToUpdate.setProject(user.getProject());

            try {
                this.userRepository.save(userToUpdate);
            } catch (Exception ex) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "One or more values are missing or invalid."
                );
            }
        }

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/..")
                        .build()
                        .normalize()
                        .toUri())
                .body("Updated successfully.");
    }
    //endregion
}