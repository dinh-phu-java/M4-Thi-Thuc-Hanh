package country.com.codegym.controller;


import country.com.codegym.model.Country;
import country.com.codegym.model.Province;
import country.com.codegym.services.S3Services;
import country.com.codegym.services.country.ICountryServices;
import country.com.codegym.services.province.IProvinceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FileController {


    @Value("${jsa.s3.bucket.url}")
    private String s3BucketUrl;

    @Autowired
    S3Services s3Services;

    @Autowired
    private IProvinceServices provinceServices;

    @Autowired
    private ICountryServices countryServices;

    @GetMapping("/upload-file")
    public String showFileUploadForm(Model theModel){
        theModel.addAttribute("province",new Province());
        theModel.addAttribute("countries",countryServices.findAll());
        return "create-province-form";
    }


    @PostMapping("/upload-file")
    public String createFileProcess(@Valid @ModelAttribute Province province
            , BindingResult theBinding
            , Model theModel
            , @RequestParam Long country
            ,@RequestParam MultipartFile file){

        if (theBinding.hasErrors()){
            theModel.addAttribute("countries",countryServices.findAll());
            return "create-province-form";
        }
        Country setCountry=countryServices.findOne(country);

        province.setCountry(setCountry);



        String uploadFolder="./upload_file/";

        String fileName=file.getOriginalFilename();

        Path uploadPath=Paths.get(uploadFolder);
        Path filePath=null;
        String image=null;
        try {
        if (!Files.exists(uploadPath)){

                Files.createDirectories(uploadPath);

        }

        InputStream inputStream=file.getInputStream();
        filePath=uploadPath.resolve(fileName);

        Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);

        if (Files.exists(filePath)){
            s3Services.uploadFile(fileName,filePath.toString());
            image=s3BucketUrl+fileName;
            province.setImage(image);
            Files.delete(filePath);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }

        provinceServices.save(province);
        theModel.addAttribute("provinces",provinceServices.findAll());
        return "index";
    }
}
