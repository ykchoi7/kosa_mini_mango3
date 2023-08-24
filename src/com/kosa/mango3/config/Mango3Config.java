package com.kosa.mango3.config;

import java.util.Scanner;

import com.kosa.mango3.customer.CustomerDAO;
import com.kosa.mango3.customer.CustomerInterface;
import com.kosa.mango3.db.DBManager;
import com.kosa.mango3.db.Mysql;
import com.kosa.mango3.db.Oracle;
import com.kosa.mango3.review.ReviewDAO;
import com.kosa.mango3.review.ReviewInterface;
import com.kosa.mango3.store.StoreDAO;
import com.kosa.mango3.store.StoreInterface;

public class Mango3Config {
//	private final static DBManager db = new Oracle();
	private final static DBManager db = new Mysql();
	private final static CustomerInterface customerDAO = new CustomerDAO();
	private final static ReviewInterface reviewDAO = new ReviewDAO();
	private final static StoreInterface storeDAO = new StoreDAO(reviewDAO);
	private final static Scanner scanner = new Scanner(System.in);
	
	public void dbConnect() {
		this.db.DBConnect();
	}
	
	public CustomerDAO customerDAO() {
		return (CustomerDAO) this.customerDAO;
	}
	
	public ReviewDAO reviewDAO() {
		return (ReviewDAO) this.reviewDAO;
	}
	
	public StoreDAO storeDAO() {
		return (StoreDAO) this.storeDAO;
	}
	
	public Scanner scanner() {
		return this.scanner;
	}
}
