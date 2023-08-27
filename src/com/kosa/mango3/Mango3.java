package com.kosa.mango3;

import java.util.Scanner;
import com.kosa.mango3.config.Mango3Config;
import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.customer.service.CustomerService;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.ReviewService;
import com.kosa.mango3.store.StoreService;

public class Mango3 {	

	private CustomerDTO loginedCustomer;
	private CustomerService customerService;
	private StoreService storeService;
	private ReviewService reviewService;
	private static Scanner sc = new Scanner(System.in);

	public Mango3() {
		customerService = new CustomerService();
	}

	public boolean loginSession() {
		if(loginedCustomer==null) {
			return false;
		} else {
			return true;
		}
	}

	public void join() {
		System.out.print("사용할 ID : ");
		String loginId=sc.nextLine();
		System.out.print("사용할 PW : ");
		String pwd=sc.nextLine();

		try {
			customerService.join(loginId, pwd);
			//비밀번호 재확인
			System.out.println("회원가입이 완료되었습니다.");
		} catch (AddException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void login() {
		System.out.print("ID : ");
		String loginId=sc.nextLine();
		System.out.print("PW : ");
		String pwd=sc.nextLine();

		try {
			loginedCustomer=customerService.login(loginId, pwd);
			System.out.println("로그인이 완료되었습니다.");
		} catch (FindException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}	
	}
	
	public void logout() {
		loginedCustomer=null;
		System.out.println("로그아웃이 완료되었습니다.");
	}

	public static void main(String[] args) {
		Mango3Config m3c = new Mango3Config();
		m3c.dbConnect();
		
		Mango3 mango3 = new Mango3();
		System.out.println("=".repeat(30));
		System.out.println("KOSA Mini Project Mango3");
		String input = "";

		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 종료");
			System.out.println("-".repeat(30));

			System.out.println("원하시는 서비스를 입력하세요 : ");
			System.out.print(">> ");
			input = sc.nextLine();

			if (input.equals("1")) {
				mango3.join();
			} else if (input.equals("2")) {
				mango3.login();
			} else if (input.equals("3")){
				System.out.println("사용이 종료되었습니다.");
				break;
			} else if (input.equals("admin")){
				//System.out.print("관리자용 패스워드 : ");
			} else {
				System.out.println("잘못 누르셨습니다.");
			}

			if(mango3.loginSession()) {
				while(true) {
					System.out.println("=".repeat(30));
					System.out.println("1. 맛집 조회");
					System.out.println("2. 내 리뷰 조회");
					System.out.println("3. 로그아웃");
					System.out.println("-".repeat(30));

					System.out.println("원하시는 서비스를 입력하세요.");
					System.out.print(">> ");
					input = sc.nextLine();

					if (input.equals("1")) {
						//mango3.storeService
					} else if (input.equals("2")) {
						//mango3.reviewService
					} else if (input.equals("3")){
						mango3.logout();
						break;
					} else {
						System.out.println("잘못 누르셨습니다.");
					} 
				}
			}
		}


	}	
}
