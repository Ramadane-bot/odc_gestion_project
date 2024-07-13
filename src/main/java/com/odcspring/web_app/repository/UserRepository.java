package com.odcspring.web_app.repository;

import com.odcspring.web_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("from User u LEFT join fetch u.roles where u.email=:email")
    Optional<User> findByEmail(String email);
}
