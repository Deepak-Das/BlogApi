package com.example.blogapi.controller;

import com.example.blogapi.model.Post;
import com.example.blogapi.model.PostResponse;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PostDto;
import com.example.blogapi.service.Imp.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostServiceImp serviceImp;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto savePost = serviceImp.createPost(postDto, categoryId, userId);
        return new ResponseEntity<>(savePost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto post=serviceImp.updatePost(postDto,postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getPosts (
            @RequestParam(value ="pageNumber" ,defaultValue ="0" ,required = false) Integer pageNumber,
            @RequestParam(value ="pageSize" ,defaultValue ="5" ,required = false)Integer pageSize,
            @RequestParam(value ="sortBy" ,defaultValue ="postId" ,required = false)String sortBy,
            @RequestParam(value ="sortDir" ,defaultValue ="asc" ,required = false)String sortDir
    ){
        return new ResponseEntity<>(serviceImp.getAllPost(pageNumber,pageSize,sortBy,sortDir),HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostbyId (@PathVariable Integer postId){
        return new ResponseEntity<>(serviceImp.getPostById(postId),HttpStatus.FOUND);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        return new ResponseEntity<>(serviceImp.getPostByUser(userId),HttpStatus.FOUND);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostbyCategory (@PathVariable Integer categoryId){
        return new ResponseEntity<>(serviceImp.getPostByCategory(categoryId),HttpStatus.FOUND);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse postDelete(@PathVariable Integer postId){
        serviceImp.deletePost(postId);
        return new ApiResponse("Post deleted","null");
    }


}
