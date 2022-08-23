package com.example.blogapi.payload;

import com.example.blogapi.model.Post;
import com.example.blogapi.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

//    private  Integer commentId;

    @NotEmpty(message = "Comment can not be empty or null")
    private String Comment;
//    private Integer userId;
    private String  userName;


}
