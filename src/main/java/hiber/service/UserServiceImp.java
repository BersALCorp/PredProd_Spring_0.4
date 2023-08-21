package hiber.service;

import hiber.dao.GenericDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final GenericDao genericDao;

    @Autowired
    public UserServiceImp(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(User user) {
        try {
            genericDao.add(user);
        } catch (Exception e) {
            System.out.println("Ошибка сохранения юзера - " + e);
        }
    }

    @Transactional
    @Override
    public void addUserWithCar(User user, Car car) {
        try {
            user.setCar(car);
            car.setUser(user);
            genericDao.add(user);
            genericDao.add(car);
        } catch (Exception e) {
            System.out.println("Ошибка сохранения связанных объектов - " + e);
        }
    }

    @Override
    public User getUserById(long id) {
        try {
            return genericDao.getById(User.class, id);
        } catch (Exception e) {
            System.out.println("Ошибка получения юзера - " + e);
        }
        return null;
    }

    @Override
    public Car getCarById(long id) {
        try {
            return genericDao.getById(Car.class, id);
        } catch (Exception e) {
            System.out.println("Ошибка получения юзера - " + e);
        }
        return null;
    }

    @Transactional
    @Override
    public void addCarToUserId(Car car, long id) {
        try {
            User temp = genericDao.getById(User.class, id);
            car.setUser(temp);
            genericDao.update(car);
            genericDao.update(temp);
        } catch (Exception e) {
            System.out.println("Ошибка добавления машины к пользователю - " + e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        try {
            return genericDao.allItems(User.class);
        } catch (Exception e) {
            System.out.println("Ошибка получения всех юзеров - " + e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
        try {
            return genericDao.allItems(Car.class);
        } catch (Exception e) {
            System.out.println("Ошибка получения всех машин - " + e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersByCarModelAndSeries(String model, int series) {
        try {
            return genericDao.getUsersByCarModelAndSeries(model, series);
        } catch (Exception e) {
            System.out.println("Ошибка получения всех юзеров по модели и серии машины - " + e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public User getFirstUserByCarModelAndSeries(String model, int series) {
        try {
            return genericDao.getFirstUserByCarModelAndSeries(model, series, "ASC");
        } catch (Exception e) {
            System.out.println("Ошибка получения первого юзера по модели и серии машины - " + e);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public User getLastUserByCarModelAndSeries(String model, int series) {
        try {
            return genericDao.getFirstUserByCarModelAndSeries(model, series, "DESC");
        } catch (Exception e) {
            System.out.println("Ошибка получения последнего юзера по модели и серии машины - " + e);
        }
        return null;
    }
}
