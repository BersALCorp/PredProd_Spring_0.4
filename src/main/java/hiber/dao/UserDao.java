package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    void add(User user);

    void update(User user);

    void delete(User user);

    User getByUserId(long id);

    List<User> listUsers();

    List<User> getUsersByCarModelAndSeries(String model, int series);

    User getFirstUserByCarModelAndSeries(String model, int series, String orderBy);
}
