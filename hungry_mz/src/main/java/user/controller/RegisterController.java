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
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.naver.storage.NcpObjectStorageService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class RegisterController {

	@Autowired
	UserService userService;
	
	//버켓 이름
	private String bucketName = "bucketcamp148";
	
	@Autowired
	final NcpObjectStorageService storageService;
	
	//회원 추가 Controller
	@GetMapping("/form")
	public String form() {
		return "member/registerform";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute UserDto dto, HttpSession session) {
		userService.insertUser(dto);
		session.setAttribute("loginstatus", true);
		session.setAttribute("username", dto.getUsername());
		return "redirect:/";
	}
	
	//아이디가 존재하면 result에 fail, 존재하지 않으면 success
	@GetMapping("/usernamecheck")
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
	
	@PostMapping("/update")
	@ResponseBody
	public void update(@ModelAttribute UserDto dto) {
		System.out.println(dto.getUser_id());
		
		userService.updateUser(dto);
	}
	
	//마이페이지를 통한 본인 탈퇴
	@GetMapping("/mypagedel")
	@ResponseBody
	public void mypageDelete(@RequestParam int user_id, HttpSession session) {
		userService.deleteUser(user_id);
		
		//모든 세션 제거
		session.removeAttribute("loginstatus");
		session.removeAttribute("usernmae");
	}
	
	@GetMapping("/mypage")
	public String goMypage(HttpSession session, Model model) {
	    // 세션에서 로그인한 사용자 아이디 가져오기
	    String username = (String) session.getAttribute("username");
	    
	    //아이디에 해당하는 dto 얻기
	    UserDto dto = userService.getSelectByUsername(username);
	    
	   
	    // JSP에서 사용할 데이터 추가
	    model.addAttribute("dto", dto);

	    // mypage.jsp로 이동
	    return "member/mypage";
	}
}
