package com.example.blogapi.repository;

import com.example.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User,Integer> {

}
