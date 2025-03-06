package data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import data.dto.MenuReviewDto;
import data.mapper.MenuReviewMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuReviewService {
	MenuReviewMapper reviewMapper;
	
	public void insertReview(MenuReviewDto dto) {
		reviewMapper.insertReview(dto);
	}
	
	public List<MenuReviewDto> getSelectReviews(int menu_id){
		return reviewMapper.getSelectReviews(menu_id);
	}
	
	public MenuReviewDto getSelectData(int review_id) {
		return reviewMapper.getSelectData(review_id);
	}
	
	public void deleteReview(int review_id) {
		reviewMapper.deleteReview(review_id);
	}
	
	public String getFilename(int review_id) {
		return reviewMapper.getFilename(review_id);
	}
}
