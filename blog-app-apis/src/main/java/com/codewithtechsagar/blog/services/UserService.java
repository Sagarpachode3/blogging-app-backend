package com.codewithtechsagar.blog.services;

import java.util.List;

import com.codewithtechsagar.blog.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);
	
	 UserDto createUser(UserDto userDto);
	 
	 UserDto updateUser(UserDto userDto, Integer userId);
	 
	 UserDto getUserById(Integer userId);
	 
	 List<UserDto> getAllUsers();
	 
	 void deleteUser(Integer userId);
	 

}
