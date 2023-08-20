package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return userDao.getByUserId(id);
    }

    @Transactional
    @Override
    public void addWithCar(User user, Car car) {
        saveUserAndCar(user, car);
    }

    @Transactional
    @Override
    public Car getCarById(long id) {
        return carDao.getByCarId(id);
    }

    @Transactional
    @Override
    public void addCar(Car car) {
        carDao.add(car);
    }

    @Transactional
    @Override
    public void saveUserAndCar(User user, Car car) {
        try {
            user.setCar(car);
            car.setUser(user);
            userDao.add(user);
            carDao.add(car);
        } catch (Exception e) {
            // Обработка ошибки
            throw new RuntimeException("Ошибка сохранения связанных объектов", e);
        }
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public void deleteCar(Car car) {
        carDao.delete(car);
    }

    @Transactional
    @Override
    public User getByUserId(long id) {
        return userDao.getByUserId(id);
    }

    @Transactional
    @Override
    public Car getByCarId(long id) {
        return carDao.getByCarId(id);
    }

    @Transactional
    @Override
    public List<Car> listCars() {
        return carDao.listCars();
    }

    @Transactional
    @Override
    public void addCarToUser(Car car, User user) {
        try {
            user.setCar(car);
            car.setUser(user);
            userDao.update(user);
            carDao.add(car);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения связанных объектов", e);
        }
    }

    @Transactional
    @Override
    public void deleteCarFromUser(User owner) {
        try {
            carDao.delete(owner.getCar());
            owner.setCar(null);
            userDao.update(owner);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения связанных объектов", e);
        }
    }

    @Transactional
    @Override
    public void deleteUserFromCar(Car car) {
        try {
            userDao.delete(car.getUser());
            car.setUser(null);
            carDao.update(car);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения связанных объектов", e);
        }
    }

    @Transactional
    @Override
    public void exchangeCarsUsers(Long id1, Long id2) {
        try {
            User user1 = userDao.getByUserId(id1);
            User user2 = userDao.getByUserId(id2);
            Car car1 = user1.getCar();
            Car car2 = user2.getCar();
            user1.setCar(car2);
            user2.setCar(car1);
            car1.setUser(user2);
            car2.setUser(user1);
            carDao.update(car1);
            carDao.update(car2);
            userDao.update(user1);
            userDao.update(user2);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения связанных объектов", e);
        }
    }

    @Transactional
    @Override
    public List<User> getUsersByCarModelAndSeries(String model, int series) {
        return userDao.getUsersByCarModelAndSeries(model, series);
    }

    @Transactional
    @Override
    public List<Car> getCarsByOwner(User owner) {
        return carDao.getCarsByOwner(owner);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
