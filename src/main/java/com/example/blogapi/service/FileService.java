package com.example.blogapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException;

    public InputStream downloadImage(String path, String fileName)throws FileNotFoundException;
}
