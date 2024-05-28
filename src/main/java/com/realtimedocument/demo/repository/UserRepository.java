package com.realtimedocument.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtimedocument.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {}
