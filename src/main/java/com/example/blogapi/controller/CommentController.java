package com.example.blogapi.controller;

import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.CommentDto;
import com.example.blogapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> addComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId
    ) {
        CommentDto SaveComment = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(SaveComment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> upadate(
            @RequestBody CommentDto commentDto,
            @PathVariable("commentId") Integer commentId
            ){
        CommentDto dto=commentService.updateComment(commentDto,commentId);

        return new ResponseEntity<>(dto,HttpStatus.OK);

    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successful", null), HttpStatus.OK);
    }

}
