package com.realtimedocument.demo.repository;

import org.springframework.stereotype.Repository;

import com.realtimedocument.demo.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

//	Optional<User> findByName(String name);
	
	void deleteByName(String name);
}
