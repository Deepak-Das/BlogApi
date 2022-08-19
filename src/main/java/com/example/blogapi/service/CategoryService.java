package com.example.blogapi.service;

import com.example.blogapi.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer userId);
    CategoryDto getCategoryById(Integer categoryId);
    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer categoryId);
}
