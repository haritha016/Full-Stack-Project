package com.jsp.usm.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jsp.usm.Dto.UserDto;
import com.jsp.usm.Dto.UserUpdateDetailDto;
import com.jsp.usm.entity.UserEntity;

public interface UserService {

	public UserEntity prepareAndSaveUser(UserDto userDto);
		
	public Page<UserEntity> getAllUser();	
	
	public UserEntity getUserById(long id);
	
	public UserEntity getUserByEmail(String email);
	
	public UserEntity getUserByContactNumberAndEmail(long contactNumber,String email);
	
	public List<UserEntity>  getUserByName(String name);
	
	public List<UserEntity> getUserByNameAndEmailOrContactNumber(String name,String email,long contactNumber);
  
	public void updateUserDetail(UserUpdateDetailDto detailDto);

}

