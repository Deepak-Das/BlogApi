package com.example.blogapi.service.Imp;

import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.model.Category;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.PostResponse;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.PostDto;
import com.example.blogapi.repository.CategoryRepo;
import com.example.blogapi.repository.PostRepo;
import com.example.blogapi.repository.UserRepo;
import com.example.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        Post newPost = modelMapper.map(postDto, Post.class);
        newPost.setAddedDate(new Date());
        newPost.setCategory(category);
        newPost.setImageName("default.png");
        newPost.setUser(user);

        return modelMapper.map(postRepo.save(newPost), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
//        Post updatePost=modelMapper.map(postDto,Post.class);
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pagerNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pagerNumber, pageSize, sort);

        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> allPost = pagePost.getContent();

        List<PostDto> postDtos = allPost.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagerNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setTotalElement(allPost.size());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User us = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = postRepo.findByUser(us);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
