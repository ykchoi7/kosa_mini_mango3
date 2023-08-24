package com.kosa.mango3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;

@Getter
public class MySql implements DBManager {
	private final String url = "jdbc:mysql://localhost:3306/mango3?serverTimezone=UTC";
	private final String user = "root";
	private final String pwd = "root";
	
	@Override
	public void DBConnect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Mysql 드라이버 로드성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Mysql DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

}
