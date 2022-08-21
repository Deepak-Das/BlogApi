package com.example.blogapi.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message = "password must be provided")
    private String password;
    private String about;

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
