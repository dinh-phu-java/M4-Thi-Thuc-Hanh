package country.com.codegym.controller;

import country.com.codegym.model.Province;
import country.com.codegym.services.country.ICountryServices;
import country.com.codegym.services.province.IProvinceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("province")
public class ProvinceController {

    @Autowired
    private IProvinceServices provinceServices;

    @Autowired
    private ICountryServices countryServices;

    @GetMapping("/create-province")
    public String showCreateProvince(Model theModel){
        theModel.addAttribute("province",new Province());
        theModel.addAttribute("countries",countryServices.findAll());
        return "create-province-form";
    }

    @PostMapping("/create")
    public String createProvince(@Valid @ModelAttribute Province province
            , BindingResult theBinding
            ,Model theModel){
        if (theBinding.hasErrors()){

            return "create-province-form";
        }

        provinceServices.save(province);

        return "index";
    }
}
