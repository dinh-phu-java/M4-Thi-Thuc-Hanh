package country.com.codegym.services;

import java.util.List;

public interface IServices<T> {
    public List<T> findAll();
    public T save(T model);
    public T findOne(Long id);
    public T remove(Long id);
}
