package com.example.blog.tutorial.repositories;

import com.example.blog.tutorial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

}
