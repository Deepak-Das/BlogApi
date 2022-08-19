package com.example.blogapi.controller;

import com.example.blogapi.payload.CategoryDto;
import com.example.blogapi.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/categories/")
public class CategoryController {

    @Autowired
    private CategoryServiceImp serviceImp;

    //Post
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto category) {
        CategoryDto saveCategory = serviceImp.createCategory(category);
        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    //Get
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        CategoryDto user = serviceImp.getCategoryById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    //Get
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = serviceImp.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.FOUND);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category, @PathVariable Integer id) {
        category.setCategoryId(id);
        CategoryDto user = serviceImp.updateCategory(category, id);

        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        serviceImp.deleteCategory(id);
        return new ResponseEntity<>("Status: Deleted", HttpStatus.FOUND);
    }

}
