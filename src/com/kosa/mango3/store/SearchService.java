package com.kosa.mango3.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
	List<StoreDTO> list = new ArrayList<>();
	
	void serviceSearch() {
		String storeName;
		int end = 5;
		
		System.out.println("검색할 음식점 이름을 입력해주세요");
		storeName = sc.next();
		list = dao.findByName(storeName);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("---------------------------");
			System.out.print(list.get(i).getStoreName() + " - ");
			System.out.print(
					"★".repeat((int) list.get(i).getGrade()) + "☆".repeat(end - (int) list.get(i).getGrade()));
			System.out.println(" (" + list.get(i).getReviewId() + ")");
			System.out.println("주소 : " + list.get(i).getAddress());
			System.out.println("연락처   : " + list.get(i).getTel());
			System.out.println("음식 종류 : " + list.get(i).getFoodType());
			System.out.println("영업시간 : " + list.get(i).getStoreHour());
		}
		
		return;
	}	
}
