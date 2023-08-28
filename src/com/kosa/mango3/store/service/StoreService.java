package com.kosa.mango3.store.service;

import java.util.Scanner;

import com.kosa.mango3.Mango3;
import com.kosa.mango3.store.dao.StoreDAOOracle;

public class StoreService {
	Scanner sc = new Scanner(System.in);
	StoreDAOOracle dao = new StoreDAOOracle();
	LocService ls = new LocService();
	TypeService ts = new TypeService();
	SearchService ss = new SearchService();

	public void serviceStore() {
		String locnum = "";
		String typenum = "";
		String num = "";
		
		while(true) { 
			System.out.println("=".repeat(30));
			System.out.println("1.지역 조회");
			System.out.println("2.음식종류 조회");
			System.out.println("3.음식점 검색");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));
			
			//번호 입력받기
			num = sc.nextLine();
			
			//번호 입력받았을 때 이동
			if (num.equals("1")) {
				ls.serviceLoc(locnum);
			} else if (num.equals("2")) {
				ts.serviceType(typenum);
			} else if (num.equals("3")) {
				ss.serviceSearch();
			} else if (num.equals("0")) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
				return;
			}
		}
	}
	
}
