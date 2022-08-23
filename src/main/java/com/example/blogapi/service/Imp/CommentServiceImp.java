package com.example.blogapi.service.Imp;

import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.model.Comment;
import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.CommentDto;
import com.example.blogapi.payload.UserDto;
import com.example.blogapi.repository.CommentRepo;
import com.example.blogapi.repository.PostRepo;
import com.example.blogapi.repository.UserRepo;
import com.example.blogapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

        Comment comment=modelMapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment saveComment=commentRepo.save(comment);
        return modelMapper.map(saveComment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment com = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

        com.setComment(commentDto.getComment());

        return modelMapper.map(commentRepo.save(com),CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        commentRepo.delete(com);
    }
}
