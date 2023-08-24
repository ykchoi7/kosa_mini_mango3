package com.kosa.mango3;

import java.util.Scanner;

import com.kosa.mango3.customer.CustomerDAO;
import com.kosa.mango3.review.ReviewDAO;
import com.kosa.mango3.store.StoreDAO;

public class Mango3 {	
	private CustomerDAO customerDAO;
	private StoreDAO storeDAO;
	private ReviewDAO reviewDAO;
	private Scanner sc;
	
	public Mango3(CustomerDAO customerDAO, StoreDAO storeDAO, ReviewDAO reviewDAO, Scanner sc) {
		this.customerDAO = customerDAO;
		this.storeDAO = storeDAO;
		this.reviewDAO = reviewDAO;
		this.sc = sc;
	}
	
	public void start() {
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
			
			System.out.println("[안내]원하시는 서비스를 눌러주세요.");
			System.out.print(">> ");
			input = sc.nextLine();
			
			if (input.equals("1")) {
				customerDAO.join();
			} else if (input.equals("2")) {
				// 맛집 조회
			} else if (input.equals("3")){
				// 내 리뷰 보기
			} else if (input.equals("4")){
				break;
			} else if (input.equals("admin")){
			} else {
				System.out.println("[안내]잘못 누르셨습니다.");
			} 
		}
	}	
}
