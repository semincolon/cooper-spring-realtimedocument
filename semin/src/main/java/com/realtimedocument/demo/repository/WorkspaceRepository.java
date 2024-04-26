package com.realtimedocument.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.realtimedocument.demo.model.Workspace;

@Repository
public interface WorkspaceRepository extends MongoRepository<Workspace, String> {

	Workspace findByName(String name);
}
