package com.fikirtepe.app.repository;


import com.fikirtepe.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     List<User> findAllByLastName(String lastName);
     List<User> findAllByCity(String city);
     Optional<User> findByUsername(String username);
}
