package com.kosa.mango3.config;

import java.sql.Connection;
import java.util.Scanner;

import com.kosa.mango3.customer.CustomerDAO;
import com.kosa.mango3.customer.CustomerInterface;
import com.kosa.mango3.db.DBManager;
import com.kosa.mango3.db.Oracle;
import com.kosa.mango3.review.ReviewDAO;
import com.kosa.mango3.review.ReviewInterface;
import com.kosa.mango3.store.dao.StoreDAO;
import com.kosa.mango3.store.dao.StoreDAOOracle;

public class Mango3Config {
	private final static DBManager db = new Oracle();
	private final static CustomerInterface customerDAO = new CustomerDAO();
	private final static ReviewInterface reviewDAO = new ReviewDAO();
	private final static StoreDAO storeDAO = new StoreDAOOracle(reviewDAO);
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
	
	public StoreDAOOracle storeDAO() {
		return (StoreDAOOracle) this.storeDAO;
	}
	
	public Scanner scanner() {
		return this.scanner;
	}
}
