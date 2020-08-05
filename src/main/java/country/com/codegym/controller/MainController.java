package country.com.codegym.controller;

import country.com.codegym.services.country.ICountryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ICountryServices countryServices;

    @GetMapping("/")
    public String homePage(Model theModel){

        return "index";
    }

}
