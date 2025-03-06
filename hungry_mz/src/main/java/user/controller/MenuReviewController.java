package user.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.naver.storage.NcpObjectStorageService;

import data.dto.MenuReviewDto;
import data.service.MenuReviewService;
import data.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menureview")
public class MenuReviewController {
	final UserService userService;
	final MenuReviewService reviewService;
	final NcpObjectStorageService storageService;
	
	//버켓 이름
	private String bucketName = "bucketcamp148";
	
	//댓글에서 업로드한 파일명
	private String uploadFilename;
	
	/*
	 * @PostMapping("/reviewupload") public String upload(@RequestParam("upload")
	 * MultipartFile upload) { //사진 다시 올릴경우 이전 사진은 삭제 if(uploadFilename!=null) {
	 * storageService.deleteFile(bucketName, "review", uploadFilename);
	 * 
	 * uploadFilename = storageService.uploadFile(bucketName, "review", upload);
	 * return uploadFilename; } }
	 */
	
	@PostMapping("/addreview")
	public void addReview(@RequestParam int menu_id, @RequestParam String content,
			HttpSession session) {
		//로그인한 아이디
		String username=(String)session.getAttribute("username");
		
		MenuReviewDto dto = MenuReviewDto.builder()
				.menu_id(menu_id)
				.content(content)
				.username(username)
				.photo(uploadFilename)
				.build();
		
		reviewService.insertReview(dto);
		uploadFilename = null;
	}
	
	@GetMapping("/reviewlist")
	public List<MenuReviewDto> getReviewList(@RequestParam int menu_id){
		System.out.println("menu_id="+menu_id);
		List<MenuReviewDto> rlist = reviewService.getSelectReviews(menu_id);
		for(int i=0; i<rlist.size();i++) {
			String username=userService.getSelectByUsername(rlist.get(i).getUsername()).getNickname();
			rlist.get(i).setUsername(username); //댓글 작성자 저장
		}
		return rlist;
	}
	
	@GetMapping("/reviewphotodel")
	public void photoDel(@RequestParam String fname) {
		storageService.deleteFile(uploadFilename, "review", fname);
		uploadFilename=null;
	}
	
	@GetMapping("/reviewdel")
	public void reviewDelete(@RequestParam int review_id) {
		//review_id에 해당하는 이미지명 얻기
		String reviewPhoto = reviewService.getSelectData(review_id).getPhoto();
		
		//null이 아닐경우 스토리지에서 삭제
		if(reviewPhoto!=null)
			storageService.deleteFile(bucketName, "review", reviewPhoto);
		
		//db에서 삭제
		reviewService.deleteReview(review_id);
	}
	
	/*
	 * @GetMapping("/review") public String review(@RequestParam int menu_id, Model
	 * model, HttpSEssion session) {
	 * 
	 * //menu_id에 대한 dto 얻기 MenuReviewDto dto =
	 * reviewService.getSelectByMenu_Id(menu_id);
	 * 
	 * //menu_id 글에 등록된 파일들 가져오기 List<String> fileList = new Vector<>(); List<> }
	 */
	
}
