package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenericDaoImp implements GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <T> void add(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public <T> void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public <T> void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> T getById(Class<T> cls, long id) {
        return sessionFactory.getCurrentSession().get(cls, id);
    }

    @Override
    @SuppressWarnings("unchecked")

    public <T> List<T> allItems(Class cls) {
        TypedQuery<T> query = sessionFactory.getCurrentSession().createQuery("from " + cls.getName());
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

    public User getFirstUserByCarModelAndSeries(String model, int series, String direction) {
        String hql = "SELECT u FROM User u INNER JOIN Car c ON u.id = c.user.id WHERE c.model = :model AND c.series = :series ORDER BY u.id " + direction;
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
