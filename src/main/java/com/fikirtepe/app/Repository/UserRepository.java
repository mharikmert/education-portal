package com.fikirtepe.app.Repository;


import com.fikirtepe.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
     List<User> findAllByLastName(String lastName);
     List<User> findAllByCity(String city);
}
