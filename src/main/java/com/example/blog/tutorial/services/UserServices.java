package com.example.blog.tutorial.services;

import com.example.blog.tutorial.payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface UserServices {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, UUID id);
    UserDTO getUserById(UUID id);
    List<UserDTO> getAllUsers();
    void deleteUser(UUID id);

}
