package com.kosa.mango3.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.review.dto.ReviewDTO;
import com.kosa.mango3.review.service.ReviewMain;
import com.kosa.mango3.store.dao.StoreDAOOracle;
import com.kosa.mango3.store.dto.StoreDTO;

public class Mango3Store {
	Scanner sc = new Scanner(System.in);
	StoreDAOOracle dao = new StoreDAOOracle();
	ReviewMain reviewMain = new ReviewMain();
	ReviewDTO reviewDto = new ReviewDTO();
	List<StoreDTO> list = new ArrayList<>();
	int end = 5; //리뷰 별 갯수 크기 지정

	//음식조회 카테고리
	public void serviceStore(String loginedId) {
		String lnum = "";
		String tnum = "";
		String num = "";

		while(true) { 
			System.out.println("=".repeat(30));
			System.out.println("1.지역 조회");
			System.out.println("2.음식종류 조회");
			System.out.println("3.음식점 검색");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));

			//번호 입력받기
			System.out.println("이동하고 싶은 메뉴 번호를 입력해주세요");
			System.out.print("입력>> ");
			num = sc.nextLine();

			//번호 입력받았을 때 이동
			if (num.equals("1")) {
				serviceLoc(lnum, loginedId);
			} else if (num.equals("2")) {
				serviceType(tnum, loginedId);
			} else if (num.equals("3")) {
				serviceSearch(loginedId);
			} else if (num.equals("0")) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
				return;
			}
		}
	}

	//위치에 따른 가게 정보 조회
	public void serviceLoc(String lnum, String loginedId) {

		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.강남");
			System.out.println("2.성수");
			System.out.println("3.잠실");
			System.out.println("4.마포");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));

			//지역번호 입력받기
			System.out.println("조회하고 싶은 장소 번호를 입력해주세요");
			System.out.print("입력>> ");
			lnum = sc.nextLine();

			if (lnum.equals("0")) {
				serviceStore(loginedId);
				return;
			} else {
				String[] location = {"강남", "성수", "잠실", "마포"};
				list = dao.findByLocation(location[(Integer.parseInt(lnum))-1]);				
			}

			//가게 리스트 정보 출력
			for (int i = 0; i < list.size(); i++) {
				System.out.println("---------------------------");
				System.out.print((i+1) +". ");
				System.out.print(list.get(i).getStoreName() + " - ");
				System.out.print("★".repeat((int)list.get(i).getGrade()) + "☆".repeat(end - (int)list.get(i).getGrade()));
				System.out.println(" (" + list.get(i).getReviewId() +")");
				System.out.println("위치 : " + list.get(i).getLocation());
				System.out.println("음식 종류 : " + list.get(i).getFoodType());
			}

			//가게 상세 정보
			System.out.println();
			System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
			System.out.print("입력>> ");

			String sdetail = sc.nextLine();
			int num=0;

			try {
				num = (Integer.parseInt(sdetail))-1;
				if(!(num==1 || num==2 || num==3 || num==4)) throw new Exception("오류");
				System.out.println("---------------------------");
				System.out.print(list.get(num).getStoreName() + " - ");
				System.out.print(
						"★".repeat((int) list.get(num).getGrade()) + "☆".repeat(end - (int) list.get(num).getGrade()));
				System.out.println(" (" + list.get(num).getReviewId() + ")");
				System.out.println("주소 : " + list.get(num).getAddress());
				System.out.println("연락처   : " + list.get(num).getTel());
				System.out.println("음식 종류 : " + list.get(num).getFoodType());
				System.out.println("영업시간 : " + list.get(num).getStoreHour());

				reviewMain.reviewMenu(list.get(num).getStoreId(), loginedId);
			} catch (Exception e) {
				System.out.println("오류");
			}

			
		}
	}


	//음식종류별 가게 조회
	public void serviceType(String tnum, String loginedId) {

		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.한식");
			System.out.println("2.중식");
			System.out.println("3.일식");
			System.out.println("4.양식");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));

			//지역번호 입력받기
			System.out.println("조회하고 싶은 음식 메뉴 번호를 입력해주세요");
			System.out.print("입력>> ");
			tnum = sc.nextLine();

			if (tnum.equals("0")) {
				serviceStore(loginedId);
				return;
			} else {
				String[] type = {"한식", "중식", "일식", "양식"};
				list = dao.findByType(type[(Integer.parseInt(tnum))-1]);				
			}

			//가게 리스트 정보 출력
			for (int i = 0; i < list.size(); i++) {
				System.out.println("---------------------------");
				System.out.print(i+1 +". ");
				System.out.print(list.get(i).getStoreName() + " - ");
				System.out.print("★".repeat((int)list.get(i).getGrade()) + "☆".repeat(end - (int)list.get(i).getGrade()));
				System.out.println(" (" + list.get(i).getReviewId() +")");
				System.out.println("위치 : " + list.get(i).getLocation());
				System.out.println("음식 종류 : " + list.get(i).getFoodType());
			}

			//번호입력받아서 가게 상세정보 출력하기
			System.out.println();
			System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
			System.out.print("입력>> ");

			String tdetail = sc.nextLine();
			int num=0;

			try {
				num = (Integer.parseInt(tdetail))-1;
				if(!(num==1 || num==2 || num==3 || num==4)) throw new Exception("오류");
				System.out.println("---------------------------");
				System.out.print(list.get(num).getStoreName() + " - ");
				System.out.print(
						"★".repeat((int) list.get(num).getGrade()) + "☆".repeat(end - (int) list.get(num).getGrade()));
				System.out.println(" (" + list.get(num).getReviewId() + ")");
				System.out.println("주소 : " + list.get(num).getAddress());
				System.out.println("연락처   : " + list.get(num).getTel());
				System.out.println("음식 종류 : " + list.get(num).getFoodType());
				System.out.println("영업시간 : " + list.get(num).getStoreHour());

				reviewMain.reviewMenu(list.get(num).getStoreId(), loginedId);
			} catch (Exception e) {
				System.out.println("오류");
			}

			
		}
	}

	//가게명으로 검색
	public void serviceSearch(String loginedId){
		String storeName;
		int end = 5;

		System.out.println("검색할 음식점 이름을 입력해주세요");
		System.out.print("입력>> ");

		storeName = sc.nextLine();
		list = dao.findByName(storeName);

		if (list.isEmpty()) {
			System.out.println("조회된 가게 정보가 없습니다.");
			return;
		}else {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("---------------------------");
				System.out.print(i+1 + ". ");
				System.out.print(list.get(i).getStoreName() + " - ");
				System.out.print("★".repeat((int)list.get(i).getGrade()) + "☆".repeat(end - (int)list.get(i).getGrade()));
				System.out.println(" (" + list.get(i).getReviewId() +")");
				System.out.println("위치 : " + list.get(i).getLocation());
				System.out.println("음식 종류 : " + list.get(i).getFoodType());					
			}
		}	


		//번호입력받아서 가게 상세정보 출력하기
		System.out.println();
		System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
		System.out.print("입력>> ");

		String detail = sc.nextLine();
		int num=0;
		
		try {
			num = (Integer.parseInt(detail));
			System.out.println("---------------------------");
			System.out.print(list.get(num).getStoreName() + " - ");
			System.out.print(
					"★".repeat((int) list.get(num).getGrade()) + "☆".repeat(end - (int) list.get(num).getGrade()));
			System.out.println(" (" + list.get(num).getReviewId() + ")");
			System.out.println("주소 : " + list.get(num).getAddress());
			System.out.println("연락처   : " + list.get(num).getTel());
			System.out.println("음식 종류 : " + list.get(num).getFoodType());
			System.out.println("영업시간 : " + list.get(num).getStoreHour());

			reviewMain.reviewMenu(list.get(num).getStoreId(), loginedId);	
		} catch (Exception e) {
			System.out.println("오류");
		}

		
	}
}
