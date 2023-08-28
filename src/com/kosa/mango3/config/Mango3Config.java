package com.kosa.mango3.config;

import com.kosa.mango3.db.DBManager;
import com.kosa.mango3.db.Oracle;

public class Mango3Config {
	private final static DBManager db = new Oracle();
//	private final static DBManager db = new Mysql();
	public void dbConnect() {
		this.db.DBConnect();
	}
	
}
