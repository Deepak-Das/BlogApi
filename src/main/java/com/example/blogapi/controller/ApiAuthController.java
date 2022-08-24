package com.example.blogapi.controller;

import com.example.blogapi.exception.DublicateDataFound;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.UserDto;
import com.example.blogapi.repository.RoleRepo;
import com.example.blogapi.repository.UserRepo;
import com.example.blogapi.util.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register/user")
    public ResponseEntity<UserDto> registerUser(
            @RequestBody UserDto userDto,
            @RequestParam(value = "type", defaultValue = AppConstants.NORMAL_USER, required = false) String type
    ) {

        if(userRepo.existsUserByEmail(userDto.getEmail())){
            throw new DublicateDataFound("Email already exist","Fail to register");
        }

        User user = modelMapper.map(userDto, User.class);

        if (type.equalsIgnoreCase(AppConstants.ADMIN_NORAMAL_USER)) {
            user.getRoles().add(roleRepo.findById(AppConstants.ADMIN_USER_ID).get());
            user.getRoles().add(roleRepo.findById(AppConstants.NORMAL_USER_ID).get());
        } else if (type.equalsIgnoreCase(AppConstants.ADMIN_USER))
            user.getRoles().add(roleRepo.findById(AppConstants.ADMIN_USER_ID).get());
        else
            user.getRoles().add(roleRepo.findById(AppConstants.NORMAL_USER_ID).get());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User newUser = userRepo.save(user);

        UserDto dto = modelMapper.map(newUser, UserDto.class);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }




}
