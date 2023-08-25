package com.kosa.mango3.store;

import java.util.Scanner;

public class StoreService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();

	void serviceStore() {
		int locnum = 0;
		int typenum = 0;
		int num;
		
		while(true) { 
			System.out.println("=".repeat(30));
			System.out.println("1.지역 조회");
			System.out.println("2.음식종류 조회");
			System.out.println("3.음식점 검색");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));
			
			//번호 입력받기
			num = sc.nextInt();
			
			//번호 입력받았을 때 이동
			if (num == 1) {
				serviceLoc(locnum);
			} else if (num == 2) {
				serviceType(typenum);
			} else if (num == 3) {
				serviceSearch();
			} else if (num == 0) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
			}
		}
	}

	
	private void serviceLoc(int locnum) {
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.강남");
			System.out.println("2.성수");
			System.out.println("3.잠실");
			System.out.println("4.마포");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));
			
			//지역번호 입력받기
			locnum = sc.nextInt();
			
			//번호 입력받았을 때 이동
			if (locnum == 1) {
				dao.findByLocation("강남");
			} else if (locnum == 2) {
				dao.findByLocation("성수");
			} else if (locnum == 3) {
				dao.findByLocation("잠실");
			} else if (locnum == 4) {
				dao.findByLocation("마포");
			} else if (locnum == 0) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
			}
		}
		
	}
	
	private void serviceType(int typenum) {
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.한식");
			System.out.println("2.중식");
			System.out.println("3.일식");
			System.out.println("4.양식");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));
			
			//지역번호 입력받기
			typenum = sc.nextInt();
			
			//번호 입력받았을 때 이동
			if (typenum == 1) {
				dao.findByType("한식");
			} else if (typenum == 2) {
				dao.findByType("중식");
			} else if (typenum == 3) {
				dao.findByType("일식");
			} else if (typenum == 4) {
				dao.findByType("양식");
			} else if (typenum == 0) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
			}
		}
		
	}
	
	private void serviceSearch() {
		String storeName;
		
		storeName = sc.next();
		dao.findByName(storeName);
	}	
	
}
