package com.kosa.mango3.store;

import java.util.List;
import java.util.Scanner;

public class TypeService {
	Scanner sc = new Scanner(System.in);
	StoreDAO dao = new StoreDAO();
//	ReviewService reviewService;
	
	public void serviceType(int typenum) {
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
				storeListPrint("한식");
			} else if (typenum == 2) {
				storeListPrint("중식");
			} else if (typenum == 3) {
				storeListPrint("일식");
			} else if (typenum == 4) {
				storeListPrint("양식");
			} else if (typenum == 0) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
			}
		}
	}
		
	private void storeListPrint(String type) {
		
		List<StoreDTO> storeList = dao.findByType(type);
		
		if (storeList.size() == 0) {
			System.out.println("[알림] 해당 타입의 가게가 없습니다.");
			return ;
		}
		
		int size = 4;
		int start = 0;
		int end = storeList.size() < size ? storeList.size() : size;
		
		System.out.println("---------------------------");
		for (int i = 0; i < storeList.size(); i++) {
			storePrint(storeList, i);
		}
		
		if (start != 0) System.out.println("p.이전 리스트");
		if (storeList.size() > end) System.out.println("n.다음 리스트");
		System.out.println("0.뒤로가기");
		System.out.println("가게 상세 정보를 확인하시려면 번호를 입력하세요.");
		System.out.print(">> ");
		sc.nextLine();
		String input = sc.nextLine();
				
		if (input.equals("p")) {
			start -= size;
			end = end%size == 0 ? end - size : end - (end%size);
		} else if (input.equals("n")) {
			start += size;
			end = storeList.size() < end + size ? storeList.size() : end + size;
		} else if (input.equals("0")) {
			return ;
		} else if (inputValidate(storeList, input)) {
			System.out.println("---------------------------");
			storePrint(storeList, Integer.parseInt(input));
			System.out.println("reviewService.reviewMenu() 호출");
////		reviewService.reviewMenu();
		sc.nextLine();
		} else {
			System.out.println("[알림]잘못 입력하였습니다, 다시 입력해 주세요.");
		}		
	}
	
	private boolean inputValidate(List<StoreDTO> storeList, String input) {

		for (char ch : input.toCharArray()) {
			if (!Character.isDigit(ch)) {
				System.out.println("[알림]잘못 입력하였습니다.");
				return false;
			}
		}
		
		if (storeList.size() < Integer.parseInt(input)) {
			System.out.println("[알림]입력한 번호의 맛집을 조회할 수 없습니다.");
			return false;
		}
		
		return true;
	}
	
	private void storePrint(List<StoreDTO> storeList, int index) {
		System.out.print(storeList.get(index).getStoreName() + " - ");
		int grade = (int) storeList.get(index).getGrade();
		System.out.println("★".repeat(grade) + "☆".repeat(5-grade) + " (" + storeList.get(index).getReviewId() +")");
		System.out.println("위치 : " + storeList.get(index).getLocation());
		System.out.println("음식 종류 : " + storeList.get(index).getFoodType());
		System.out.println("---------------------------");
		
//		System.out.println("reviewService.reviewMenu() 호출");
////		reviewService.reviewMenu();
//		sc.nextLine();
	}
	
}
