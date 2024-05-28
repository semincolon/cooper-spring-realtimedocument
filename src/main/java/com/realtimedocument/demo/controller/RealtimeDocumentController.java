package com.realtimedocument.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.realtimedocument.demo.model.User;
import com.realtimedocument.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RealtimeDocumentController {

//	private final UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "main/join";
	}


//	@PostMapping("/leave")
//	public String leave(@ModelAttribute("userId") String userId) {
//		User user = userService.getUser(userId);
//		userService.deleteUser(user);
//		return "main/leave";
//	}
}

