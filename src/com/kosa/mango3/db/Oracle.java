package com.kosa.mango3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle implements DBManager {
	
	@Override
	public void DBLoad() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC Oracle 드라이버 로드성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public Connection DBConnect() {
	
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return conn;
	}
	
	@Override
	public void DBClose(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
}
