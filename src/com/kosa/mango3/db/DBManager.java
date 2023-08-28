package com.kosa.mango3.db;

import java.sql.Connection;

public interface DBManager {
	/**
	 * DB로드
	 */
	void DBLoad();
	
	/**
	 * DB연결
	 */
	Connection DBConnect();
	
	/**
	 * DB연결 종료
	 */
	void DBClose(Connection conn);
	
}
