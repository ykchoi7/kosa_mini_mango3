package com.kosa.mango3.store;

import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.review.ReviewDAO;

public class LocService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
	List<StoreDTO> list;
	int end = 5;
	
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
			switch (locnum) {
			case 1 :
				storePrint("강남");
				break;
			case 2 :
				storePrint("성수");
				break;
			case 3 :
				storePrint("잠실");
				break;
			case 4 :
				storePrint("마포");
				break;
			case 0 :
				return;
			default :
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
			}
		}	
	}
	
	void storePrint(String name) {
		List<StoreDTO> list = dao.findByLocation(name);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("---------------------------");
			System.out.print(list.get(i).getStoreName() + " - ");
			System.out.print("★".repeat((int)list.get(i).getGrade()) + "☆".repeat(end - (int)list.get(i).getGrade()));
			System.out.println(" (" + list.get(i).getReviewId() +")");
			System.out.println("위치 : " + list.get(i).getLocation());
			System.out.println("음식 종류 : " + list.get(i).getFoodType());
		}
		
		//번호입력받아서 가게 상세정보 출력하기
		System.out.println();
		System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
		try {
			int num = sc.nextInt();
			System.out.println("---------------------------");
			System.out.print(list.get(num).getStoreName() + " - ");
			System.out.print(
					"★".repeat((int) list.get(num).getGrade()) + "☆".repeat(end - (int) list.get(num).getGrade()));
			System.out.println(" (" + list.get(num).getReviewId() + ")");
			System.out.println("주소 : " + list.get(num).getAddress());
			System.out.println("연락처   : " + list.get(num).getTel());
			System.out.println("음식 종류 : " + list.get(num).getFoodType());
			System.out.println("영업시간 : " + list.get(num).getStoreHour());
		} catch (Exception e) {
			System.out.println("없는 번호를 입력했습니다");
		}
		
//		ReviewDAO.ReviewService();
	}
}
