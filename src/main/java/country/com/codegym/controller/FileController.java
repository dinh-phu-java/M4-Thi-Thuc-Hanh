package country.com.codegym.controller;

import org.springframework.core.io.Resource;
import country.com.codegym.services.S3Services;
import country.com.codegym.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FileController {





    @Autowired
    S3Services s3Services;

    @GetMapping("/upload-file")
    public String showFileUploadForm(){
        return "file-upload-page";
    }


    @PostMapping("/upload-file")
    public String createFileProcess(@RequestParam MultipartFile file){
        String uploadFolder="./upload_file/";

        String fileName=file.getOriginalFilename();

        Path uploadPath=Paths.get(uploadFolder);
        Path filePath=null;
        try {
        if (!Files.exists(uploadPath)){

                Files.createDirectories(uploadPath);

        }

        InputStream inputStream=file.getInputStream();
        filePath=uploadPath.resolve(fileName);

        Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);

        if (Files.exists(filePath)){
            s3Services.uploadFile(fileName,filePath.toString());
            Files.delete(filePath);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }



        return "file-upload-page";
    }
}
