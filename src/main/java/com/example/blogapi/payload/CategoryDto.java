package com.example.blogapi.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryDto {

    private int categoryId;
    @NotEmpty(message = "must have title")
    private String categoryTitle;
    @NotEmpty(message = "must have description")
    private String categoryDescription;
}
