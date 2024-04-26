package com.realtimedocument.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextMessage {

	private String workspaceName;
	private User user;
	private List<User> totalMembers;
	private List<String> onlineMembers;
	private String message;
}
