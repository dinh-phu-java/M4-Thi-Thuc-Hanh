package country.com.codegym.services.province;

import country.com.codegym.dao.ProvinceDAO;
import country.com.codegym.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServices implements IProvinceServices{
    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Province> findAll() {
        return provinceDAO.findAll();
    }

    @Override
    public Province save(Province model) {
        return provinceDAO.save(model);
    }

    @Override
    public Province findOne(Long id) {
        Optional<Province> optional=provinceDAO.findById(id);
        Province province=null;
        if (optional.isPresent())
        {
            province=optional.get();
        }
        return province;
    }

    @Override
    public Province remove(Long id) {
        Province removeProvince=findOne(id);
        if (removeProvince!=null){
            provinceDAO.deleteById(id);
        }
        return removeProvince;
    }
}
