package com.fikirtepe.app.Repository;

import com.fikirtepe.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
