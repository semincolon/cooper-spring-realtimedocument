package com.realtimedocument.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "workspace")
public class Workspace {

	@Id
	private String name;
	private User generator;
	private List<User> member;
	private List<String> onlineMembers;
	
	@Builder
	public Workspace(String name, User generator, List<User> member, List<String> onlineMembers) {
		this.name = name;
		this.generator = generator;
		this.member = member;
		this.onlineMembers = onlineMembers;
	}
}
