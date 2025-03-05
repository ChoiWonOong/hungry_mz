package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import data.dto.UserDto;
import data.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import com.naver.storage.NcpObjectStorageService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterController {

	@Autowired
	final UserService userService;
	
	//버켓 이름
	private String bucketName = "bucketcamp148";
	
	@Autowired
	final NcpObjectStorageService storageService;
	
	//회원 추가 Controller
	@GetMapping("/form")
	public String form() {
		return "user/userform";
	}
	
	//아이디가 존재하면 result에 fail, 존재하지 않으면 success
	@GetMapping("/idcheck")
	@ResponseBody
	public Map<String, String> usernameCheck(@RequestParam String username){
		Map<String, String> map = new HashMap<>();
		
		if(userService.UsernameCheck(username)) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
	
	//회원 삭제 Controller
	@GetMapping("/delete")
	public String deleteUser(@RequestParam int user_id) {
		userService.deleteUser(user_id);
		return "redirect:./list";
		
	}
}
