package com.example.blog.tutorial.repositories;

import com.example.blog.tutorial.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
