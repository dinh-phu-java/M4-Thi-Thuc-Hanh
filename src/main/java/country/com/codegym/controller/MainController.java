package country.com.codegym.controller;

import country.com.codegym.services.country.ICountryServices;
import country.com.codegym.services.province.IProvinceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private IProvinceServices provinceServices;

    @Autowired
    private ICountryServices countryServices;

    @GetMapping("/")
    public String homePage(Model theModel){
        theModel.addAttribute("provinces",provinceServices.findAll());
        return "index";
    }

}
