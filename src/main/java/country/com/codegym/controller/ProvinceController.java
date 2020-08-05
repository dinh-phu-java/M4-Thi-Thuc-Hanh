package country.com.codegym.controller;

import country.com.codegym.model.Country;
import country.com.codegym.model.Province;
import country.com.codegym.services.country.ICountryServices;
import country.com.codegym.services.province.IProvinceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            , Model theModel
    , @RequestParam Long country){
        if (theBinding.hasErrors()){
            theModel.addAttribute("countries",countryServices.findAll());
            return "create-province-form";
        }
        Country setCountry=countryServices.findOne(country);

        province.setCountry(setCountry);
        provinceServices.save(province);

        theModel.addAttribute("provinces",provinceServices.findAll());
        return "index";
    }
    @GetMapping("/edit/{id}")
    private String editForm(@PathVariable Long id,Model theModel){
        Province editProvince=provinceServices.findOne(id);
        theModel.addAttribute("province",editProvince);
        theModel.addAttribute("countries",countryServices.findAll());
        return "create-province-form";
    }


    @GetMapping("/delete/{id}")
    private String deleteProvince(@PathVariable Long id,Model theModel){
//        Province deleteProvince=provinceServices.findOne(id);
        provinceServices.remove(id);
        theModel.addAttribute("provinces",provinceServices.findAll());
        return "index";
    }
    @GetMapping("/detail/{id}")
    private String detailProvince(@PathVariable Long id,Model theModel){
        Province detailProvice=provinceServices.findOne(id);
        theModel.addAttribute("province",detailProvice);
        return "detail";
    }
}
