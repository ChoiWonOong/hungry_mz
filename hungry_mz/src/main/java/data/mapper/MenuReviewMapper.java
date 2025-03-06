package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.MenuReviewDto;

@Mapper
public interface MenuReviewMapper {
	public void insertReview(MenuReviewDto dto);
	public List<MenuReviewDto> getSelectReviews(int menu_id);
	public MenuReviewDto getSelectData(int review_id);
	public void deleteReview(int review_id);
	public String getFilename(int review_id);
}
