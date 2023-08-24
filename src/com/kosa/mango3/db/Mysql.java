package com.kosa.mango3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql implements DBManager {

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
		String url = "jdbc:mysql://localhost:3306/mango3?serverTimezone=UTC";
		String user = "root";
		String password = "root";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Mysql DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

}
