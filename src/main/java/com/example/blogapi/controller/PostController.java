package com.example.blogapi.controller;

import com.example.blogapi.model.PostResponse;
import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.PostDto;
import com.example.blogapi.service.FileService;
import com.example.blogapi.service.PostService;
import com.example.blogapi.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService serviceImp;

    @Autowired
    private FileService fileService;

    @Value("${project.images}")
    private String path;


    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto savePost = serviceImp.createPost(postDto, categoryId, userId);
        return new ResponseEntity<>(savePost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto post = serviceImp.updatePost(postDto, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {

        return new ResponseEntity<>(serviceImp.getAllPost(pageNumber, pageSize, sortBy, sortDir), HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostbyId(@PathVariable Integer postId) {
        return new ResponseEntity<>(serviceImp.getPostById(postId), HttpStatus.FOUND);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(serviceImp.getPostByUser(userId), HttpStatus.FOUND);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostbyCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(serviceImp.getPostByCategory(categoryId), HttpStatus.FOUND);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostDto>> getPostBySearch(
            @RequestParam(value = "keyword") String keyword
    ) {
        System.out.println("Search");
        List<PostDto> postDtos = serviceImp.searchByTitle(keyword);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse postDelete(@PathVariable Integer postId) {
        serviceImp.deletePost(postId);
        return new ApiResponse("Post deleted", "null");
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @Param(value = "image") MultipartFile image,
            @PathVariable Integer postId
    ) {

        PostDto postDto=serviceImp.getPostById(postId);
        String file_name="default.png";
        try {
             file_name=fileService.uploadImage(path,image);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        postDto.setImageName(file_name);
        PostDto updatePost=serviceImp.updatePost(postDto,postId);



        return new ResponseEntity<>(updatePost,HttpStatus.OK);

    }


}
