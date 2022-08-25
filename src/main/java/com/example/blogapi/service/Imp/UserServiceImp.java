package com.example.blogapi.service.Imp;

import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.model.User;
import com.example.blogapi.payload.UserDto;
import com.example.blogapi.repository.UserRepo;
import com.example.blogapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        System.out.println(userDto.toString());
        User user = dtoToUser(userDto);
        User saveUser = userRepo.save(user);
        return userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(userRepo.save(dtoToUser(userDto)));
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = userRepo.findAll().stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//        System.out.println(user.toString());
        user.getRoles().removeAll(user.getRoles());
        userRepo.deleteById(userId);
    }

    UserDto userToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    User dtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
