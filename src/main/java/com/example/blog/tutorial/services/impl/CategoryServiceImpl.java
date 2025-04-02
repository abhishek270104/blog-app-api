package com.example.blog.tutorial.services.impl;

import com.example.blog.tutorial.entities.Category;
import com.example.blog.tutorial.payloads.CategoryDTO;
import com.example.blog.tutorial.repositories.CategoryRepository;
import com.example.blog.tutorial.services.CategoryServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, UUID id) {
        return null;
    }
    @Override
    public void deleteCategory(UUID id) {

    }
    @Override
    public CategoryDTO getCategoryById(UUID id) {
        return null;
    }
    @Override
    public List<CategoryDTO> getAllCategory() {
        return null;
    }
}
