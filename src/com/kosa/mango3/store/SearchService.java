package com.kosa.mango3.store;

import java.util.Scanner;

public class SearchService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
	
	void serviceSearch() {
		String storeName;
		
		storeName = sc.next();
		dao.findByName(storeName);
	}	
}
