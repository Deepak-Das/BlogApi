package com.example.blogapi.repository;

import com.example.blogapi.model.Category;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    @Override
    List<Post> findAll();

    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String keyword);
}
