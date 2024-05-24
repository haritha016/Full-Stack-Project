package com.jsp.usm.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MySqlConfiguration {

	public  HikariDataSource getDataSourse()
	{
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("tiger");
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/m5_config");
		return new HikariDataSource(hikariConfig);
		
		
			}
}
