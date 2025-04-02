package com.example.blog.tutorial.services;

import com.example.blog.tutorial.payloads.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryServices {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, UUID id);
    void deleteCategory(UUID id);
    CategoryDTO getCategoryById(UUID id);
    List<CategoryDTO> getAllCategory();
}
