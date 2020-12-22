package com.fikirtepe.app.DAO;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryJpaImplementation  implements UserRepository {

    //entity manager to manipulating data
    @PersistenceContext
    private EntityManager entityManager;

    //create user with entity manager
    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    //list all the users in database
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User" , User.class).getResultList();
    }

    //find a user with id and return
    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }
}
