package com.example.blogapi;

import com.example.blogapi.model.Role;
import com.example.blogapi.repository.RoleRepo;
import com.example.blogapi.util.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("123"));

        Set<Role> roleSet=new HashSet<>();

        roleSet.add(new Role(501, AppConstants.ADMIN_USER));
        roleSet.add(new Role(502,AppConstants.NORMAL_USER));

        roleRepo.saveAll(roleSet);


    }
}
