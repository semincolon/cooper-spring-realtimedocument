package com.realtimedocument.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.realtimedocument.demo.model.User;
import com.realtimedocument.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
		// 유저가 있는지 확인하고 만약 없다면 새로 생성하여 DB에 저장
		public void checkUser(User user) {
			if (userRepository.findById(user.getId()).equals(Optional.empty())) {
				System.out.println("새로운 유저 생성: " + user.getName());
				userRepository.save(user);
			} else {
				System.out.println("기존 유저: " + user.getName());
			}
		}
	
		// 유저 검색 메소드
		public User getUser(String userId) {
			User user = userRepository.findById(userId).orElse(null);
			return user;
		}
		
		// 유저 삭제
		public void deleteUser(User user) {
			userRepository.delete(user);
			System.out.println("삭제된 User: " + user.getName());
		}
}
