package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User getUserById(long id);

    void addWithCar(User user, Car car);

    Car getCarById(long id);

    void addCar(Car car);

    void saveUserAndCar(User user, Car car);

    void deleteUser(User user);

    void deleteCar(Car car);

    User getByUserId(long id);

    Car getByCarId(long id);

    List<Car> listCars();

    List<User> listUsers();

    void addCarToUser(Car car, User user);

    void deleteCarFromUser(User owner);

    void deleteUserFromCar(Car car);

    void exchangeCarsUsers(User owner1, User owner2);

    List<User> getUsersByCarModelAndSeries(String model, int series);

    List<Car> getCarsByOwner(User owner);
}
