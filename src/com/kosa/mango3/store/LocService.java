package com.kosa.mango3.store;

import java.util.List;
import java.util.Scanner;

public class LocService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
	
	void serviceLoc(int locnum) {
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
	
	void storePrint(List<StoreDTO> list, String name) {
		list = dao.findByLocation(name);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("---------------------------");
			System.out.print(list.get(i).getStoreName() + " - ");
			System.out.println("★".repeat((int)list.get(i).getGrade()) + " (" + list.get(i).getReviewId() +")");
			System.out.println("위치 : " + list.get(i).getLocation());
			System.out.println("음식 종류 : " + list.get(i).getFoodType());
		}
	}
}
