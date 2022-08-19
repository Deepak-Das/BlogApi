package com.example.blogapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer categoryId;

    @NotEmpty(message = "Must have title")
    @Column(name = "title")
    String categoryTitle;

    @NotEmpty(message = "Must have description")
    @Column(name = "description")
    String categoryDescription;
}
