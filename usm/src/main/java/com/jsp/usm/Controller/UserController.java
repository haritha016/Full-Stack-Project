package com.jsp.usm.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.usm.Dto.UserDto;
import com.jsp.usm.Dto.UserUpdateDetailDto;
import com.jsp.usm.Service.UserService;
import com.jsp.usm.entity.UserEntity;

//@Controller
@RestController
public class UserController {
	
	private static Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	 
	@Autowired
	UserService userService;
	
//	@PostMapping(value = "/createUser")
	@RequestMapping(value = "/createUser",method = RequestMethod.POST)
	public @ResponseBody UserEntity createUser(@RequestBody UserDto dto) {
		LOGGER.info("executing creat", dto);
		return userService.prepareAndSaveUser(dto);
		
	}
	
	@GetMapping(value="/getAllUsers")
	public @ResponseBody Page<UserEntity> getAllUser(){
		
		return  userService.getAllUser();
	}

	@GetMapping(value = "/getUserById/{userId}")
	public  @ResponseBody UserEntity getUserById(@PathVariable("userId") long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(value = "/getUserByEmail")
	public @ResponseBody UserEntity getUserByEmail(
			@RequestHeader("email") String email) {
		
		return userService.getUserByEmail(email);
		
	}
	
	@GetMapping(value = "/getUserByContactAndEmail,")
	public  UserEntity getUserByContactAndEmail(long contactNumber,String email) {
		return userService.getUserByContactNumberAndEmail(contactNumber, email);
	}
	
	@GetMapping("/getUserByName")
	public List<UserEntity> getUserByName(String name){
		
		return userService.getUserByName(name);
				
	}
	
	@GetMapping(value = "/getUserByNameAndEmailOrContactNumberByAsc")
	public List<UserEntity> getUserByNameAndEmailOrContactNumberByAsc(String name,String email,long contactNumber){
		return userService.getUserByNameAndEmailOrContactNumber(name, email, contactNumber);
		
	}
	
	@PostMapping(value ="/updateUserDetails")
	public void updateUserDetail(@RequestBody UserUpdateDetailDto detailDto) {
		userService.updateUserDetail(detailDto);
	
	}
}
