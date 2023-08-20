package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarDao {
    void add(Car car);

    void update(Car car);

    void delete(Car car);

    Car getByCarId(long id);

    List<Car> listCars();

    List<Car> getCarsByOwner(User owner);
}
