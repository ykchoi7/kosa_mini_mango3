package com.kosa.mango3.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.store.dao.StoreDAOOracle;
import com.kosa.mango3.store.dto.StoreDTO;

public class Test {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StoreDAOOracle dao = new StoreDAOOracle();
		StoreDTO dto = new StoreDTO();
		List<StoreDTO> list = dao.findByLocation("강남");
		PageTest pt = new PageTest(list);
		
//		dao.storeInfo();
		
//		pt.next();
//		System.out.println();
//		pt.next();
//		System.out.println();
//		pt.before();
		
//		List<StoreDTO> list = dao.findByLocation("강남");
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
		
//		String name = sc.next();
//		List<StoreDTO> list = dao.findByName(name);
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("---------------------------");
//			System.out.print(list.get(i).getStoreName() + " - ");
//			System.out.println("★".repeat((int)list.get(i).getGrade()) + " (" + list.get(i).getReviewId() +")");
//			System.out.println("위치 : " + list.get(i).getLocation());
//			System.out.println("음식 종류 : " + list.get(i).getFoodType());
//		}
		
//		Mango3Store m3 = new Mango3Store();
//		m3.serviceStore();
//		
	}

}
