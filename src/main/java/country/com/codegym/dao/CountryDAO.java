package country.com.codegym.dao;

import country.com.codegym.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDAO  extends JpaRepository<Country,Long> {
}
