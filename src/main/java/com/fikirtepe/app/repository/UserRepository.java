package com.fikirtepe.app.repository;


import com.fikirtepe.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
     List<User> findAllByLastName(String lastName);
     List<User> findAllByCity(String city);
}
