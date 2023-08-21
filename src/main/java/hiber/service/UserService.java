package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void addUserWithCar(User user, Car car);

    User getUserById(long id);

    Car getCarById(long id);

    void addCarToUserId(Car car, long id);

    List<User> listUsers();

    List<Car> listCars();

    List<User> getUsersByCarModelAndSeries(String model, int series);

    User getFirstUserByCarModelAndSeries(String model, int series);

    User getLastUserByCarModelAndSeries(String model, int series);
}
