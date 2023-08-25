package com.kosa.mango3.store;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		StoreDAO dao = new StoreDAO();
		StoreDTO dto = new StoreDTO();
		PageTest pt = new PageTest();
		
//		dao.storeInfo();
		
//		pt.next();
//		System.out.println();
//		pt.next();
//		System.out.println();
//		pt.before();
		
//		List<StoreDTO> list = dao.findByLocation();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("---------------------------");
//			System.out.print(list.get(i).getStoreName() + " - ");
//			System.out.println("★".repeat((int)list.get(i).getGrade()) + " (" + list.get(i).getReviewId() +")");
//			System.out.println("위치 : " + list.get(i).getLocation());
//			System.out.println("음식 종류 : " + list.get(i).getFoodType());
//		}
		
//		List<StoreDTO> list = dao.findByType();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("---------------------------");
//			System.out.print(list.get(i).getStoreName() + " - ");
//			System.out.println("★".repeat((int)list.get(i).getGrade()) + " (" + list.get(i).getReviewId() +")");
//			System.out.println("위치 : " + list.get(i).getLocation());
//			System.out.println("음식 종류 : " + list.get(i).getFoodType());
//		}
		
		StoreService ss = new StoreService();
//		ss.serviceStore(2);
		
	}

}
