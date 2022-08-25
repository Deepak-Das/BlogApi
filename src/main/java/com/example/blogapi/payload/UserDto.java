package com.example.blogapi.payload;


import com.example.blogapi.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int userId;
    @NotNull(message = "Null value not allowed")
    @Size(min = 4, message = "word length must have 4 letter")
    private String name;
    @Email(message = "invalid email")
    @NotNull(message = "Null value not allowed")
    private String email;
    @NotEmpty(message = "password must be provided")
    private String password;
    private String about;

    private Set<Role> roles =new HashSet<>();

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
