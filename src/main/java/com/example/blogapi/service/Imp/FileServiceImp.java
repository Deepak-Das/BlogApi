package com.example.blogapi.service.Imp;

import com.example.blogapi.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

//        FileOutputStream stream=new FileOutputStream()
        Files.copy(file.getInputStream(), Paths.get(filePath));
        
        
        

        return fileName;
    }
}
