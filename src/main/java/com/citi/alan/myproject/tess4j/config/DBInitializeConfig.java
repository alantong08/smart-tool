package com.citi.alan.myproject.tess4j.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
//	@PostConstruct
//	public void initialize(){
//		try {
//			Connection connection = dataSource.getConnection();
//			ScriptRunner sr = new ScriptRunner(connection);
//			String sqlScriptPath = "/Users/gongjiao/git/smart-tool/src/main/resources/db/dml.sql";
//			Reader reader = new BufferedReader(new FileReader(sqlScriptPath));
//			sr.runScript(reader);
//			connection.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@PostConstruct
	public void initializeData(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			createRoleData(statement);
//			createOrderDetail(statement);
			statement.close();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

    private void createRoleData(Statement statement) throws SQLException {
        statement.executeUpdate("delete from role");
        statement.executeUpdate("insert into role values (1, 'ADMIN')");
        statement.executeUpdate("insert into role values (2, 'USER')");
    }
}
