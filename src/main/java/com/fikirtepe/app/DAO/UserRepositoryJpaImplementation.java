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
public class UserRepositoryJpaImplementation implements UserRepository {

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {

    }
}
