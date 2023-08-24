package com.kosa.mango3.db;

import java.sql.Connection;

public interface DBManager {
	
	/**
	 * DB연결
	 * @return 
	 */
	Connection DBConnect();
}
