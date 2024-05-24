package com.jsp.usm.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.usm.Dto.UserDto;
import com.jsp.usm.Dto.UserUpdateDetailDto;
import com.jsp.usm.Repository.UserRepository;
import com.jsp.usm.entity.UserEntity;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	DozerBeanMapper beanMapper= new DozerBeanMapper();
	
	@Override
	public UserEntity prepareAndSaveUser(UserDto userDto) {
	  UserEntity userEntity=beanMapper.map(userDto, UserEntity.class);
	 userEntity= userRepository.save(userEntity);
	 
	 Map<String,String> requestBody=new HashMap<String,String>();
	 
	 requestBody.put("smsContent", "Hi your account has been created");
	 
	 ObjectMapper mapper=new ObjectMapper();
	 try {
		 HttpHeaders httpHeaders=new HttpHeaders();
		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		 
		 String request=mapper.writeValueAsString(requestBody);
		 HttpEntity<String>httpEntity=new HttpEntity<>(request,httpHeaders);
		 ResponseEntity<String>response=restTemplate.exchange("http://localhost:8081/Integration/sendSms",
				 HttpMethod.POST,httpEntity,String.class);
		 
		 System.out.println(response.getBody());
	 }catch (Exception e) {
	e.printStackTrace();
	}
	 
	 
	  return userEntity;
	}


	@Override
	public Page<UserEntity> getAllUser() {
	Sort sort=Sort.by(Sort.Direction.ASC,"email");
	PageRequest pageRequest= PageRequest.of(0, 2, sort);
	
		Page<UserEntity> findAll = userRepository.findAll(pageRequest);
		return findAll;
	}


	@Override
	public UserEntity getUserById(long id) {
		Optional<UserEntity> optional= userRepository.findById(id);
		return optional .get();
	}


	@Override
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Override
	public UserEntity getUserByContactNumberAndEmail(long contactNumber, String email) {
		return  userRepository.findByContactNumberAndEmail(contactNumber, email);
	}


	@Override
	public List<UserEntity> getUserByName(String name) {
		
		return userRepository.findByName(name);
	}


	@Override
	public List<UserEntity> getUserByNameAndEmailOrContactNumber(String name, String email, long contactNumber) {
		
		return userRepository.findByNameAndEmailOrContactNumberOrderByEmailAsc(name, email, contactNumber);
	}

     
	@Override
	public void updateUserDetail(UserUpdateDetailDto detailDto) {
		
		String name=detailDto.getName();
		String city=detailDto.getCity();
		long pin=detailDto.getPinCode();
		long id=detailDto.getAltkey();
		
		userRepository.updateUserDetails(name, city, pin, id);
		
	}
}
