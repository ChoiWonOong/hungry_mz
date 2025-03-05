package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
