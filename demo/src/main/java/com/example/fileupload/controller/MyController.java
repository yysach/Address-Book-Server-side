package com.example.fileupload.controller;

import java.io.IOException;

import com.example.fileupload.helper.FileUploadHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MyController {

    @PostMapping("upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
        
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain file");
        }
        if(file.getContentType().equals("image/jpeg")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Jpeg content type is allowed");
        }

        // file upload code
        // C:\Users\yysac\OneDrive\Desktop\VSCodeJava\demo\src\main\resources\static


        FileUploadHelper fileUploadHelper = new FileUploadHelper();
        boolean f = fileUploadHelper.uploadFile(file);

        if(f){
            return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("couldn't uploaded");
        }
    }
}
