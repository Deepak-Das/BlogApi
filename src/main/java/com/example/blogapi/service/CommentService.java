package com.example.blogapi.service;

import com.example.blogapi.payload.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
    public CommentDto updateComment(CommentDto commentDto,Integer commentId);

    public void deleteComment(Integer commentId);
}
