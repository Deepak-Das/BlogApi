package com.example.blogapi.service.Imp;

import com.example.blogapi.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        String fileName = UUID.randomUUID() + name.substring(name.lastIndexOf("."));
       String filePath=path+File.separator+fileName;

        File createFile=new File(path);
        
        if(!createFile.exists()){
            createFile.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream downloadImage(String path,String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream inputStream= new FileInputStream(fullPath);
        return inputStream;
    }
}
