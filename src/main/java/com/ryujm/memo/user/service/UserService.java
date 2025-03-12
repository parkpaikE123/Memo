package com.ryujm.memo.user.service;

import org.springframework.stereotype.Service;

import com.ryujm.memo.common.MD5HashingEncoder;
import com.ryujm.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	// 다른 생성자가 없이 autowired를 위한 생성자가 있는 경우는 @Autowired 생략가능
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
				String loginId
				, String password
				, String name
				, String email) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encryptPassword, name, email);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
