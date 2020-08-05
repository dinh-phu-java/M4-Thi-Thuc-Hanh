package country.com.codegym.services.country;

import country.com.codegym.dao.CountryDAO;
import country.com.codegym.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServices implements ICountryServices{
    @Autowired
    private CountryDAO countryDAO;

    @Override
    public List<Country> findAll() {
        return countryDAO.findAll();
    }

    @Override
    public Country save(Country model) {
        return null;
    }

    @Override
    public Country findOne(Long id) {
        return null;
    }

    @Override
    public Country remove(Long id) {
        return null;
    }
}
