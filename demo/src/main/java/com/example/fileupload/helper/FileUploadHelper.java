package com.example.fileupload.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    //public final static String UPLOAD_DIR="C:\\Users\\yysac\\OneDrive\\Desktop\\VSCodeJava\\demo\\src\\main\\resources\\static";
    
    public final String UPLOAD_DIR=new ClassPathResource("/static/image/").getFile().getAbsolutePath();


    public FileUploadHelper() throws IOException{

    }

    public boolean uploadFile(MultipartFile multipartFile){
        

        boolean flag = false;

        try{
            InputStream  iss = multipartFile.getInputStream();

            byte data[]= new byte[iss.available()];
            iss.read(data);

            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());

            fos.write(data);
            fos.flush();
        
            fos.close();

            flag = true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
