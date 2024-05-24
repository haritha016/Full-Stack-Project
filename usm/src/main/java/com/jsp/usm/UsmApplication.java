package com.jsp.usm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages= {"com.jsp.usm" , "com.jsp.integration"} , exclude = DataSourceAutoConfiguration.class)
public class UsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsmApplication.class, args);
	}

}
