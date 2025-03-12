package com.ryujm.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/user/login-view")
	public String login() {
		return "user/login";
	}
	
}
