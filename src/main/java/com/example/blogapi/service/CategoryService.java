package com.example.blogapi.service;

import com.example.blogapi.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto userDto);
    CategoryDto updateCategory(CategoryDto userDto,Integer userId);
    CategoryDto getCategoryById(Integer userId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Integer userId);
}
