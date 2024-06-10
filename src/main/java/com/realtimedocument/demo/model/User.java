package com.realtimedocument.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import groovy.transform.EqualsAndHashCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
	
	@Id
	@Column(name = "user_id")
	private String id;

	@Column(name = "user_name")
	private String name;

	@Builder
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
