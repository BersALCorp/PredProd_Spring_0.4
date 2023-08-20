package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getByUserId(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByCarModelAndSeries(String model, int series) {
        String hql = "SELECT DISTINCT u FROM User u INNER JOIN Car c ON u.id = c.user.id WHERE c.model = :model AND c.series = :series";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }
//   Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
//   В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.
}
