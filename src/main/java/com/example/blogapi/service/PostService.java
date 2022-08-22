package com.example.blogapi.service;

import com.example.blogapi.model.PostResponse;
import com.example.blogapi.payload.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //post
    PostDto getPostById(Integer postId);

    //delete
    void deletePost(Integer postId);

    PostResponse getAllPost(Integer PagerNumber, Integer PageSize,String sortBy,String sortDir);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchByTitle(String keyword);

//    PostDto uploadImage(String path, MultipartFile file);

}
