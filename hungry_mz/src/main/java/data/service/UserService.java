package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dto.UserDto;
import data.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	UserMapper userMapper;
	
	public boolean UsernameCheck(String username) {
		return userMapper.checkUsername(username)==1?true:false;
	}
	
	public void insertUser(UserDto dto) {
		userMapper.insertUser(dto);
	}
	
	public boolean loginCheck(String username, String password) {
		return userMapper.loginCheck(username, password)==1?true:false;
	}
	
	public void deleteUser(int user_id) {
		userMapper.deleteUser(user_id);
	}
}
