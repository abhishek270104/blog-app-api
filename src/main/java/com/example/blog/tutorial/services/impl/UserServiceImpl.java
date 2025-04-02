package com.example.blog.tutorial.services.impl;

import com.example.blog.tutorial.entities.User;
import com.example.blog.tutorial.exceptions.ResourceNotFoundException;
import com.example.blog.tutorial.payloads.UserDTO;
import com.example.blog.tutorial.repositories.UserRepository;
import com.example.blog.tutorial.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("user", "id", id)));
        ReflectionUtils.doWithFields(UserDTO.class, field -> {
            field.setAccessible(true);
            Object value = field.get(userDTO);

            if (value != null && !"id".equals(field.getName())) {
                Field userField = ReflectionUtils.findField(User.class, field.getName());
                if (userField != null) {
                    userField.setAccessible(true);
                    userField.set(user, value);
                }
            }
        });

        User updatedUser = userRepository.save(user);

        return userToDTO(updatedUser);
    }
    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        return userToDTO(user);
    }
    @Override
    public void deleteUser(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> (new ResourceNotFoundException("user", "id", id)));
        userRepository.delete(user);
    }

    private User dtoToUser(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
