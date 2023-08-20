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
    public void add(User user) {
        try {
            userDao.add(user);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сохранения юзера - ", e);
        }
    }

    @Transactional
    @Override
    public void addUserWithCar(User user, Car car) {
        try {
            user.setCar(car);
            car.setUser(user);
            userDao.add(user);
            carDao.add(car);
        } catch (Exception e) {
            // Обработка ошибки
            throw new RuntimeException("Ошибка сохранения связанных объектов - ", e);
        }
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        try {
            return userDao.getByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения юзера - ", e);
        }
    }

    @Transactional
    @Override
    public void addCarToUserId(Car car, long id) {
        try {
            User temp = userDao.getByUserId(id);
            temp.setCar(car);
            car.setUser(temp);
            carDao.update(car);
            userDao.update(temp);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка добавления машины к пользователю - ", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        try {
            return userDao.listUsers();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения всех юзеров - ", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
        try {
            return carDao.listCars();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения всех машин - ", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersByCarModelAndSeries(String model, int series) {
        try {
            return userDao.getUsersByCarModelAndSeries(model, series);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения всех юзеров по модели и серии машины - ", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getFirstUserByCarModelAndSeries(String model, int series) {
        try {
            return userDao.getFirstUserByCarModelAndSeries(model, series, "ASC");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения первого юзера по модели и серии машины - ", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getLastUserByCarModelAndSeries(String model, int series) {
        try {
            return userDao.getFirstUserByCarModelAndSeries(model, series, "DESC");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения последнего юзера по модели и серии машины - ", e);
        }
    }
}
