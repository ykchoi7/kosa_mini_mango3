package com.kosa.mango3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.store.dto.StoreDTO;
import com.kosa.mango3.store.dao.StoreDAOOracle;

public class Mango3Store {
	Scanner sc = new Scanner(System.in);
	StoreDAOOracle dao = new StoreDAOOracle();
	//private StoreService serivce;
	List<StoreDTO> list = new ArrayList<>();
	int end = 5; //리뷰 별 갯수 크기 지정

	//음식조회 카테고리
	public void serviceStore() {
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
				return;
			}
		}
	}
	
	//위치에 따른 가게 정보 조회
	public void serviceLoc(int lnum) {
		
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.강남");
			System.out.println("2.성수");
			System.out.println("3.잠실");
			System.out.println("4.마포");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));
			
			//지역번호 입력받기
			lnum = sc.nextInt();			

			String[] location = {"강남", "성수", "잠실", "마포"};
			List<StoreDTO> list = dao.findByLocation(location[lnum-1]);
			
			//가게 리스트 정보 출력
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
				int sdetail = sc.nextInt();
				System.out.println("---------------------------");
				System.out.print(list.get(sdetail).getStoreName() + " - ");
				System.out.print(
						"★".repeat((int) list.get(sdetail).getGrade()) + "☆".repeat(end - (int) list.get(sdetail).getGrade()));
				System.out.println(" (" + list.get(sdetail).getReviewId() + ")");
				System.out.println("주소 : " + list.get(sdetail).getAddress());
				System.out.println("연락처   : " + list.get(sdetail).getTel());
				System.out.println("음식 종류 : " + list.get(sdetail).getFoodType());
				System.out.println("영업시간 : " + list.get(sdetail).getStoreHour());
			} catch (Exception e) {
				System.out.println("없는 번호를 입력했습니다");
			}
		}	
	}
	
	//음식종류별 가게 조회
	public void serviceType(int tnum) {
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.한식");
			System.out.println("2.중식");
			System.out.println("3.일식");
			System.out.println("4.양식");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));
			
			//지역번호 입력받기
			tnum = sc.nextInt();
			
			String[] type = {"한식", "중식", "일식", "양식"};
			List<StoreDTO> storeList = dao.findByType(type[tnum-1]);

			//가게 리스트 정보 출력
			for (int i = 0; i < list.size(); i++) {
				System.out.println("---------------------------");
				System.out.print(storeList.get(i).getStoreName() + " - ");
				System.out.print("★".repeat((int)storeList.get(i).getGrade()) + "☆".repeat(end - (int)storeList.get(i).getGrade()));
				System.out.println(" (" + storeList.get(i).getReviewId() +")");
				System.out.println("위치 : " + storeList.get(i).getLocation());
				System.out.println("음식 종류 : " + storeList.get(i).getFoodType());
			}
			
			//번호입력받아서 가게 상세정보 출력하기
			System.out.println();
			System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
			try {
				int tdetail = sc.nextInt();
				System.out.println("---------------------------");
				System.out.print(storeList.get(tdetail).getStoreName() + " - ");
				System.out.print(
						"★".repeat((int) storeList.get(tdetail).getGrade()) + "☆".repeat(end - (int) storeList.get(tdetail).getGrade()));
				System.out.println(" (" + storeList.get(tdetail).getReviewId() + ")");
				System.out.println("주소 : " + storeList.get(tdetail).getAddress());
				System.out.println("연락처   : " + storeList.get(tdetail).getTel());
				System.out.println("음식 종류 : " + storeList.get(tdetail).getFoodType());
				System.out.println("영업시간 : " + storeList.get(tdetail).getStoreHour());
			} catch (Exception e) {
				System.out.println("없는 번호를 입력했습니다");
			}
		}
	}
	
	//가게명으로 검색
	public void serviceSearch() {
		String storeName;
		int end = 5;
		
		System.out.println("검색할 음식점 이름을 입력해주세요");
		storeName = sc.next();
		list = dao.findByName(storeName);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println("---------------------------");
			System.out.print(list.get(i).getStoreName() + " - ");
			System.out.print(
					"★".repeat((int) list.get(i).getGrade()) + "☆".repeat(end - (int) list.get(i).getGrade()));
			System.out.println(" (" + list.get(i).getReviewId() + ")");
			System.out.println("주소 : " + list.get(i).getAddress());
			System.out.println("연락처   : " + list.get(i).getTel());
			System.out.println("음식 종류 : " + list.get(i).getFoodType());
			System.out.println("영업시간 : " + list.get(i).getStoreHour());
		}
		return;
	}
}
