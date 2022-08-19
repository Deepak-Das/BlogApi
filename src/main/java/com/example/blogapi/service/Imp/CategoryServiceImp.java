package com.example.blogapi.service.Imp;

import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.model.Category;
import com.example.blogapi.payload.CategoryDto;
import com.example.blogapi.repository.CategoryRepo;
import com.example.blogapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto,Category.class);
        return modelMapper.map(categoryRepo.save(category),CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category saveCategory=categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Categery","categoryId",categoryId));
        Category updateCategory=modelMapper.map(categoryDto,Category.class);
        return modelMapper.map(categoryRepo.save(updateCategory),CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Categery","categoryId",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtos=categoryRepo.findAll().stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category saveCategory=categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Categery","categoryId",categoryId));
        categoryRepo.delete(saveCategory);
    }
}
