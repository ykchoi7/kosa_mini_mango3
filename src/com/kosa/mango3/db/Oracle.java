package com.kosa.mango3.db;

import lombok.Getter;

@Getter
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
}
