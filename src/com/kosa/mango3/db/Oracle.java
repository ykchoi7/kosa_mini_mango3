package com.kosa.mango3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;

@Getter
public class Oracle implements DBManager {
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "hr";
	private final String password = "hr";
	
	@Override
	public void DBConnect() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC Oracle 드라이버 로드성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		Connection conn = null;
				
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}
