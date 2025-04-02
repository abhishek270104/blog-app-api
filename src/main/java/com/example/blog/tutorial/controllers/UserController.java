package com.example.blog.tutorial.controllers;

import com.example.blog.tutorial.entities.User;
import com.example.blog.tutorial.payloads.ApiResponse;
import com.example.blog.tutorial.payloads.UserDTO;
import com.example.blog.tutorial.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServices userService;

    @Autowired
    public UserController(UserServices userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO getUserDTO = this.userService.getUserById(id);
        return ResponseEntity.ok(getUserDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsersDTO = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsersDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable UUID id) {
        UserDTO updateUserDTO = this.userService.updateUser(userDTO, id);
        return ResponseEntity.ok(updateUserDTO);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }
}
