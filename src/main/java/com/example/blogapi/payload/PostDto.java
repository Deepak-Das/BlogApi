package com.example.blogapi.payload;

import com.example.blogapi.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    @NotEmpty(message = "Must have title")
    private String title;
    @NotEmpty(message = "Must have content")
    private String content;
    private String imageName;
    private Date addedDate;


    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comment;
}
