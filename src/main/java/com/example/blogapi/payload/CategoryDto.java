package com.example.blogapi.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryDto {

    int categoryId;
    String categoryTitle;
    String categoryDescription;
}
