//package com.kosa.mango3.review;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import com.kosa.mango3.db.Oracle;
//
//public class ReviewService {
//	static ReviewDAO rwd = new ReviewDAO();
//	static List<ReviewDTO> reviewList;	
//	
//	static java.util.Scanner sc = new java.util.Scanner(System.in);
//	public static void main(String[] args) {
//		
//	}
//	
//	public static void findReview() {
//		Long storeId = null;
//		reviewList = rwd.selectByStore(storeId);
//		while(true) {
//			System.out.println();
//			System.out.println(">>리뷰 보기<<");
//			System.out.println("1. 전체 리뷰 보기");
//			System.out.println("2. 평점이 '맛있다'인 리뷰만 보기");
//			System.out.println("3. 평점이 '괜찮다'인 리뷰만 보기");
//			System.out.println("4. 평점이 '별로'인 리뷰만 보기");
//			System.out.println("0. 뒤로 가기");
//			System.out.print(">입력:");
//			String input = sc.next();
//	
//			switch(Integer.parseInt(input)) {
//			case 1 :
//				rwd.selectByStore(storeId);
//				break;
//			case 2 :
//				rwd.selectByGrade(storeId, 5);
//				break;
//			case 3 :
//				rwd.selectByGrade(storeId, 3);
//				break;
//			case 4 :
//				rwd.selectByGrade(storeId, 1);
//				break;
//			case 0 :
//				reviewMenu();
//				break;
//			}
//		}
//	}
//	
//	public static void reviewMenu() {
//
//	}
//	
//	public static void printReview() {
//		for(int i = 0; i<reviewList.size(); i++) {
//			ReviewDTO d = reviewList.get(i);
//			String stringGrade = stringGrade(d.getGrade());
//			System.out.println("'--------------------------------------------");	
//			System.out.println("1. " + d.getCustomerDTO().getLoginId() + " - " + stringGrade);
//			System.out.println("ㄴ " + d.getComment());
//			System.out.println("                               " + d.getRegdate());
//		}
//	}
//	
// 	public static String stringGrade(int grade) {
//		String stringGrade = null;
//		switch(grade) {
//			case 5 :
//				stringGrade = "맛있다";
//				break;
//			case 3 :
//				stringGrade = "괜찮다";
//				break;
//			case 1 :
//				break;
//		}
//		return stringGrade;
//	}
//}



