package com.jsp.Integration;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class ChannelController {
	
	@PostMapping(value = "/sendSms")
	public String sendSms(@RequestBody Map<String ,String> data) {
		System.out.println(data.get("smsContent"));
		System.out.println("hihihihihihih");
		return "sms send successfully" ;

	}

}
