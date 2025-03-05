package data.dto;

import java.security.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("UserDto")
public class UserDto {
	private int user_id;
	private String username;
	private String password;
	private String nickname;
	private String hp;
	private Timestamp registered_date;
}
