package com.kosa.mango3;

import java.util.Scanner;

import com.kosa.mango3.config.Mango3Config;
import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.customer.service.CustomerService;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.service.ReviewMain;
import com.kosa.mango3.store.StoreAdmin;
import com.kosa.mango3.store.service.StoreService;

public class Mango3 {	

	private CustomerDTO loginedCustomer;
	private CustomerService customerService;
	private StoreService storeService;
	private ReviewMain reviewService;
	private static Scanner sc = new Scanner(System.in);

	public Mango3() {
		customerService = new CustomerService();
		storeService=new StoreService();
		reviewService=new ReviewMain();
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

		System.out.print("PW 확인 : ");
		String checkPwd=sc.nextLine();
		if(!pwd.equals(checkPwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			join();
			return;
		}

		try {
			customerService.join(loginId, pwd);
			System.out.println("회원가입이 완료되었습니다.");
		} catch (AddException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			join();
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
			System.out.print("재로그인을 하려면 1을 입력해주세요 : ");
			String reLo=sc.nextLine();
			if(reLo.equals("1")) login();
			else System.out.println("로그인이 취소됩니다.");
		}	
	}

	public void logout() {
		loginedCustomer=null;
		System.out.println("로그아웃이 완료되었습니다.");
	}

	public void adminLogin() {
		System.out.print("관리자용 패스워드 : ");
		String adminPwd=sc.nextLine();
		if(adminPwd.equals("admin")) {
			System.out.println("관리자용 페이지로 연결합니다.");
			StoreAdmin admin=new StoreAdmin();
			while(true) {
				System.out.println("1. 음식점 추가");
				System.out.println("2. 음식점 정보 수정");
				System.out.println("3. 음식점 삭제");
				System.out.println("4. 관리자 계정 연결 종료");
				System.out.print("수행할 항목 : ");
				String num=sc.nextLine();
				if(num.equals("4")) {
					System.out.println("관리자 계정 연결을 종료합니다.");
					break;
				}
				admin.adminPage(num);
			}
		} else System.out.println("패스워드가 올바르지 않습니다.");
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
				mango3.adminLogin();

			} else {
				System.out.println("잘못 누르셨습니다.");
			}

			if(mango3.loginSession()) {
				while(true) {
					System.out.println("=".repeat(30));
					System.out.println("1. 맛집 조회");
					System.out.println("2. 내 리뷰 조회");
					System.out.println("3. 비밀번호 변경");
					System.out.println("4. 로그아웃");
					System.out.println("0. 회원 탈퇴");
					System.out.println("-".repeat(30));

					System.out.println("원하시는 서비스를 입력하세요.");
					System.out.print(">> ");
					input = sc.nextLine();

					if (input.equals("1")) {
						mango3.storeService.serviceStore();
					} else if (input.equals("2")) {
						mango3.reviewService.myReviewList(mango3.loginedCustomer.getLoginId(), 0);
					} else if (input.equals("3")){

						System.out.print("기존 비밀번호 : ");
						String pwd=sc.nextLine();
						if(mango3.loginedCustomer.getPwd().equals(pwd)) {
							System.out.print("변경할 비밀번호 : ");
							String newPwd=sc.nextLine();
							System.out.print("비밀번호 확인 : ");
							String checkPwd=sc.nextLine();
							if(newPwd.equals(checkPwd)) mango3.customerService.changePwd(mango3.loginedCustomer.getLoginId(), newPwd);
							else System.out.println("비밀번호가 일치하지 않습니다.");
						} else System.out.println("비밀번호가 올바르지 않습니다.");

					} else if (input.equals("4")){
						mango3.logout();
						break;
					} else if (input.equals("0")) {
						
						System.out.print("비밀번호 확인 : ");
						String pwd=sc.nextLine();
						if(mango3.loginedCustomer.getPwd().equals(pwd)) {
							System.out.println("[※] 회원 탈퇴를 진행하시겠습니까?");
							System.out.println("탈퇴 시 계정은 복구되지 않습니다. (0 입력 시 회원 탈퇴 진행)");
							System.out.print("입력 >> ");
							String fin=sc.nextLine();
							if(fin.equals("0")) {
								mango3.customerService.removeCustomer(mango3.loginedCustomer.getLoginId());
								mango3.loginedCustomer=null;
								System.out.println("로그인 화면으로 돌아갑니다.");
								break;
							} else System.out.println("회원 탈퇴를 취소합니다.");
						} else System.out.println("비밀번호가 올바르지 않습니다.");
						
					} else System.out.println("잘못 누르셨습니다.");

				}
			}
		}

	}	
}
