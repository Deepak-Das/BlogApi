package com.example.blogapi.repository;

import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Integer> {

    public User getUserByEmail(String email);

    public boolean existsUserByEmail(String email);

}
