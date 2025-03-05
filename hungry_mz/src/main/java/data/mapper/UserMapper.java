package data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.dto.UserDto;

@Mapper
public interface UserMapper {
	public int checkUsername(String username);
	public List<UserDto> getAllUsers();
	public void insertUser(UserDto dto);
	public int loginCheck(String username, String password);
	public void deleteUser(int user_id);
}
