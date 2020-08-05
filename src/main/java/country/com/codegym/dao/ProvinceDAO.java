package country.com.codegym.dao;

import country.com.codegym.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceDAO extends JpaRepository<Province,Long> {
}
