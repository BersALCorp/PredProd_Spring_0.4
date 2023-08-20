package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().persist(car);
    }

    @Override
    public void update(Car car) {
        sessionFactory.getCurrentSession().saveOrUpdate(car);
    }

    @Override
    public void delete(Car car) {
        sessionFactory.getCurrentSession().delete(car);
    }

    @Override
    public Car getByCarId(long id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    public List<Car> getCarsByOwner(User owner) {
        String hql = "FROM Car WHERE user = :owner";
        Query<Car> query = sessionFactory.getCurrentSession().createQuery(hql, Car.class);
        query.setParameter("owner", owner);
        return query.getResultList();
    }
}
