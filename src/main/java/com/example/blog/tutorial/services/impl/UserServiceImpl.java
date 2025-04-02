package com.example.blog.tutorial.services.impl;

import com.example.blog.tutorial.entities.User;
import com.example.blog.tutorial.exceptions.ResourceNotFoundException;
import com.example.blog.tutorial.payloads.UserDTO;
import com.example.blog.tutorial.repositories.UserRepository;
import com.example.blog.tutorial.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return this.userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("user", "id", id)));
        for (Field field : UserDTO.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(userDTO);
                if (value != null && !"id".equals(field.getName())) {
                    Field userField = User.class.getDeclaredField(field.getName());
                    userField.setAccessible(true);
                    userField.set(user, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        User updatedUser = userRepository.save(user);

        return this.userToDTO(updatedUser);
    }
    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userToDTO).toList();
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        return this.userToDTO(user);
    }
    @Override
    public void deleteUser(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("user", "id", id)));
        userRepository.delete(user);
    }

    private User dtoToUser(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAbout(user.getAbout());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
