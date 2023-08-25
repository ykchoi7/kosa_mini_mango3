package com.kosa.mango3.store;

import java.util.Scanner;

import com.kosa.mango3.Mango3;

public class StoreService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
	LocService ls = new LocService();
	TypeService ts = new TypeService();
	SearchService ss = new SearchService();
	Mango3 m3 = new Mango3();

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
				ls.serviceLoc(locnum);
			} else if (num == 2) {
				ts.serviceType(typenum);
			} else if (num == 3) {
				ss.serviceSearch();
			} else if (num == 0) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
				return;
			}
		}
	}
	
}
