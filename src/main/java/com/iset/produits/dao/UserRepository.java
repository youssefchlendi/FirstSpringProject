package com.iset.produits.dao;

import java.util.Optional;

import com.iset.produits.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(" select u from User u where u.username = ?1")
    Optional<User> findUserWithName(String username);
}
