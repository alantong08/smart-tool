package com.citi.alan.myproject.tess4j.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			createUserInfo(statement);
			createOrderDetail(statement);
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

    private void createUserInfo(Statement statement) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS user_info");
        statement.executeUpdate(
        		"CREATE TABLE user_info(" +
        		"user_id INTEGER Primary key, " +
        		"user_name varchar(30) not null," +
        		"nick_name varchar(30) not null," +
        		"password varchar(30) not null," +
        		"alipayAccount varchar(100) not null," + 
        		"mobile varchar(30) not null)" 
        		);
    }
    
    private void createOrderDetail(Statement statement) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS order_detail");
        statement.executeUpdate(
                "CREATE TABLE order_detail(" +
                "id INTEGER Primary key, " +
                "userName varchar(30) not null," +
                "nickName varchar(30) not null," +
                "scanDate date not null," +
                "orderNum varchar(50) not null," +
                "merchantName varchar(30) not null," +
                "actualAmount varchar(20) not null," +
                "transferType varchar(20) not null," +
                "activityType varchar(20) not null," +
                "rate varchar(20) not null," +
                "alipayAccount varchar(100) not null," + 
                "comment varchar(50) not null)" 
                );
    }
}
