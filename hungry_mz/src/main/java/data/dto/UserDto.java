package data.dto;


import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Alias("UserDto")
public class UserDto {
	private int user_id;
	private String username;
	private String password;
	private String nickname;
	private String hp;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Timestamp registered_date;
}
