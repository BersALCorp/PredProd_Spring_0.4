package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GenericDao {
    <T> void add(T entity);

    <T> void update(T entity);

    <T> void delete(T entity);

    <T> T getById(Class<T> cls, long id);

    <T> List<T> allItems(Class cls);

    List<User> getUsersByCarModelAndSeries(String model, int series);

    User getFirstUserByCarModelAndSeries(String model, int series, String orderBy);
}
