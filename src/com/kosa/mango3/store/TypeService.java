package com.kosa.mango3.store;

import java.util.Scanner;

public class TypeService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
	
	void serviceType(int typenum) {
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
}
