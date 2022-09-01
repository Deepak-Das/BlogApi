package com.example.blogapi.controller;


import com.example.blogapi.payload.ApiResponse;
import com.example.blogapi.payload.UserDto;
import com.example.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//User controller
@RestController
@RequestMapping("api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    //Post
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto saveUser = userService.createUser(userDto);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    //Get
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    //Get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.FOUND);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        UserDto user = userService.updateUser(userDto, id);
        userDto.setUserId(id);

        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }


    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Integer id) {

        userService.deleteUser(id);
        return new ApiResponse("User deleted","null");
    }


}
