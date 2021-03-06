package web.dao;

import web.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User u where u.firstName = :paramName ");
        query.setParameter("paramName", name);
        return query.getSingleResult();
    }

    @Override
    public User getUserById(long id) {
        User user1 =  sessionFactory.getCurrentSession()
                .get(User.class, id);
        return user1;
    }

    @Override
    public void deleteUser(Long id) {
        User user2 = getUserById(id);
        sessionFactory.getCurrentSession().delete(user2);
    }

    @Override
    public void updateUser(User user) {

        sessionFactory.getCurrentSession().update(user);

    }


}
