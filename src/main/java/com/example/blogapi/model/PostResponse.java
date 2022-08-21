package com.example.blogapi.model;

import com.example.blogapi.payload.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private Integer PageNumber;
    private Integer PageSize;
    private Integer totalPage;
    private Integer totalElement;
    private boolean isLastPage;
}
