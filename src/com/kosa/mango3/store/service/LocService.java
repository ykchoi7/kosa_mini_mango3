package com.kosa.mango3.store.service;

import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.store.dao.StoreDAOOracle;
import com.kosa.mango3.store.dto.StoreDTO;

public class LocService {
	Scanner sc = new Scanner(System.in);
	StoreDAOOracle dao = new StoreDAOOracle();
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

			String[] location = {"강남", "성수", "잠실", "마포"};
			//storePrint(location[locnum+1]);
			List<StoreDTO> list = dao.findByLocation(location[locnum+1]);
			
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
