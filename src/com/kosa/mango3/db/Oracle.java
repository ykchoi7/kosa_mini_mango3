package com.kosa.mango3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle implements DBManager {
	
	@Override
	public Connection DBConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC Oracle 드라이버 로드성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}
