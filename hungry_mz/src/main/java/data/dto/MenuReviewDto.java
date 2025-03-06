package data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("MenuReviewDto")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuReviewDto {
	private int review_id;
	private int menu_id;
	private String username;
	private String content;
	private String photo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone ="Asia/Seoul")
	private Timestamp write_date;
}
