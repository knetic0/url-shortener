package com.mehmetsolak.urlshortener.repository;

import com.mehmetsolak.urlshortener.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailOrUsername(String email, String username);
    boolean existsUserByEmailOrUsername(String email, String username);
    Optional<User> findByUsername(String username);
}
