package user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.service.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
	@Autowired
	UserService userService;
	
	@GetMapping("/user/login")
	public Map<String, String> login(
			@RequestParam String username, @RequestParam String password,
			HttpSession session
			){
		Map<String, String> map = new HashMap<>();
		boolean chk = userService.loginCheck(username, password);
		
		if(chk) {
			session.setMaxInactiveInterval(60*60*4); //4시간
			session.setAttribute("loginstatus", "success");
			session.setAttribute("username", username);
		}
		map.put("result",  chk?"success":"fail");
		
		return map;
		
		
	}
	
	
	
}
