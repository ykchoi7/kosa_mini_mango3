package com.kosa.mango3;

import java.util.Scanner;

import com.kosa.mango3.customer.CustomerDAO;
import com.kosa.mango3.review.MyReviewService;
import com.kosa.mango3.review.ReviewDAO;
import com.kosa.mango3.store.StoreDAO;
import com.kosa.mango3.store.admin.AdminStoreService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mango3 {	
	private CustomerDAO customerDAO;
	private StoreDAO storeDAO;
	private ReviewDAO reviewDAO;
	private MyReviewService myReviewService;
	private AdminStoreService adminStoreService;
		
	public void start() {
		Scanner sc = new Scanner(System.in)
				;
		System.out.println("=".repeat(30));
		System.out.println("KOSA Mini Project Mango3");
		String input = "";
		
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.회원 가입");
			System.out.println("2.맛집 조회");
			System.out.println("3.내 리뷰 보기");
			System.out.println("4.서비스 종료");
			System.out.println("-".repeat(30));
			
			System.out.println("[알림]원하시는 서비스를 눌러주세요.");
			System.out.print(">> ");
			input = sc.nextLine();
			
			if (input.equals("1")) {
				customerDAO.join();
			} else if (input.equals("2")) {
				// 맛집 조회
			} else if (input.equals("3")){
				// String customerId = customerDAO.login();
				// if (customerId.equals("-1")) break;
				customerDAO.login();
				myReviewService.myReviewList("test1");
				
			} else if (input.equals("4")){
				break;
			} else if (input.equals("admin")){
				customerDAO.join();
				adminStoreService.management();
			} else {
				System.out.println("[알림]잘못 누르셨습니다.");
			} 
		}

	}	
}
