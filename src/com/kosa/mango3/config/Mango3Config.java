package com.kosa.mango3.config;

import com.kosa.mango3.customer.CustomerDAO;
import com.kosa.mango3.customer.CustomerInterface;
import com.kosa.mango3.db.DBManager;
import com.kosa.mango3.db.MySql;
import com.kosa.mango3.review.MyReviewService;
import com.kosa.mango3.review.ReviewDAO;
import com.kosa.mango3.review.ReviewInterface;
import com.kosa.mango3.store.StoreDAO;
import com.kosa.mango3.store.StoreInterface;
import com.kosa.mango3.store.admin.AdminStoreDAO;
import com.kosa.mango3.store.admin.AdminStoreInterface;
import com.kosa.mango3.store.admin.AdminStoreService;

public class Mango3Config {
//	private final DBManager db = new Oracle();
	private final DBManager db = new MySql();
	private final CustomerInterface customerDAO = new CustomerDAO();
	private final ReviewInterface reviewDAO = new ReviewDAO((MySql) db);
	private final StoreInterface storeDAO = new StoreDAO((ReviewDAO) reviewDAO);
	private final MyReviewService myReviewService = new MyReviewService((ReviewDAO) reviewDAO);
	private final AdminStoreInterface adminStoreDAO = new AdminStoreDAO((MySql) db);
	private final AdminStoreService adminStoreService = new AdminStoreService((AdminStoreDAO) adminStoreDAO);
	
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
	
	public MyReviewService myReviewService() {
		return this.myReviewService;
	}
	
	public AdminStoreService adminStoreService() {
		return this.adminStoreService;
	}
}
