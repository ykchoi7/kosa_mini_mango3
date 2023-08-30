package com.kosa.mango3;

import java.util.Scanner;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.customer.service.CustomerService;
import com.kosa.mango3.db.DBManager;
import com.kosa.mango3.db.Oracle;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.ReviewMain;
import com.kosa.mango3.store.StoreAdmin;
import com.kosa.mango3.store.StoreMain;

public class Mango3 {	
	private DBManager db;
	private CustomerDTO loginedCustomer;
	private CustomerService customerService;
	private StoreMain storeMain;
	private ReviewMain reviewMain;
	private static Scanner sc = new Scanner(System.in);

	public Mango3() {
		this.db = new Oracle();
		this.customerService = new CustomerService();
		this.storeMain=new StoreMain();
		this.reviewMain=new ReviewMain();
	}

	/**
	 * 현재 사용자의 로그인 상태 확인
	 * @return true : 로그인 / false : 로그아웃
	 */
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

		System.out.print("PW 재확인 : ");
		String checkPwd=sc.nextLine();
		if(!pwd.equals(checkPwd)) {
			System.out.println("( つ｡>﹏<｡)つ PW가 일치하지 않습니다.");
			return;
		}

		try {
			customerService.join(loginId, pwd);
			System.out.println("༼ つ ◕_◕ ༽つ 회원가입이 완료되었습니다.");
		} catch (AddException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}

	public void login() {
		System.out.print("ID : ");
		String loginId=sc.nextLine();
		System.out.print("PW : ");
		String pwd=sc.nextLine();

		try {
			loginedCustomer=customerService.login(loginId, pwd);
			System.out.println("༼ つ ◕_◕ ༽つ 로그인이 완료되었습니다.");
		} catch (FindException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("༼ つ ◕_◕ ༽つ 재로그인을 하려면 1을 입력해주세요");
			System.out.print("✔️ ");
			String reLo=sc.nextLine();
			if(reLo.equals("1")) login();
			else System.out.println("༼ つ ◕_◕ ༽つ 로그인이 취소됩니다.");
		}	
	}

	public void logout() {
		loginedCustomer=null;
		System.out.println("༼ つ ◕_◕ ༽つ 로그아웃이 완료되었습니다.");
	}

	public void adminLogin() throws FindException {
		System.out.print("관리자용 PW : ");
		String adminPwd=sc.nextLine();
		if(adminPwd.equals("admin")) {
			System.out.println("관리자용 페이지로 연결합니다.");
			StoreAdmin admin=new StoreAdmin();
			while(true) {
				System.out.println("\n0. 음식점 조회");
				System.out.println("1. 음식점 추가");
				System.out.println("2. 음식점 정보 수정");
				System.out.println("3. 음식점 삭제");
				System.out.println("4. 관리자 계정 연결 종료");
				System.out.print("✔️ ");
				String num=sc.nextLine();
				if(num.equals("4")) {
					System.out.println("관리자 계정 연결을 종료합니다.");
					break;
				}
				admin.adminPage(num);
			}
		} else System.out.println("PW가 올바르지 않습니다.");
	}

	public static void main(String[] args) {
		Mango3 mango3 = new Mango3();
		mango3.db.DBLoad();
		
		System.out.println("=".repeat(40));
		System.out.println("    __  ___                       _____\r\n"
				+ "   /  |/  /___ _____  ____ _____ |__  /\r\n"
				+ "  / /|_/ / __ `/ __ \\/ __ `/ __ \\ /_ < \r\n"
				+ " / /  / / /_/ / / / / /_/ / /_/ /__/ / \r\n"
				+ "/_/  /_/\\__,_/_/ /_/\\__, /\\____/____/  \r\n"
				+ "                   /____/           "
				+"\n\t\t\t\tby 3조");
		String input = "";

		while(true) {
			System.out.println("=".repeat(40));
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 종료");
			System.out.println("-".repeat(40));

			System.out.println("༼ つ ◕_◕ ༽つ 원하시는 서비스를 입력하세요.");
			System.out.print("✔️ ");
			input = sc.nextLine();
			System.out.println("-".repeat(40));

			if (input.equals("1")) {
				mango3.join();
			} else if (input.equals("2")) {
				mango3.login();
			} else if (input.equals("3")){
				System.out.println("( つ｡◕﹏◕｡)つ 사용이 종료되었습니다.");
				System.out.println("=".repeat(40));
				System.out.println("         ____                __\r\n"
						+ "        / __ )__  _____     / /\r\n"
						+ "       / __  / / / / _ \\   / / \r\n"
						+ "      / /_/ / /_/ /  __/  /_/  \r\n"
						+ "     /_____/\\__, /\\___/  (_)   \r\n"
						+ "           /____/              "
						+ "\n\t\t\tMade by.도열E와I들");
				System.out.println("=".repeat(40));
				break;
			} else if (input.equals("admin")){
				try {
					mango3.adminLogin();
				} catch (FindException e) {
					System.out.println(e.getMessage());
				}

			} else {
				
				System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다.");
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

					System.out.println("༼ つ ◕_◕ ༽つ 원하시는 서비스를 입력하세요.");
					System.out.print("✔️ ");
					input = sc.nextLine();

					if (input.equals("1")) {
						mango3.storeMain.serviceStore(mango3.loginedCustomer.getLoginId());
					} else if (input.equals("2")) {
						mango3.reviewMain.myReviewList(mango3.loginedCustomer.getLoginId());
					} else if (input.equals("3")){

						System.out.print("기존 PW : ");
						String pwd=sc.nextLine();
						if(mango3.loginedCustomer.getPwd().equals(pwd)) {
							System.out.print("변경할 PW : ");
							String newPwd=sc.nextLine();
							System.out.print("PW 재확인 : ");
							String checkPwd=sc.nextLine();
							if(newPwd.equals(checkPwd)) mango3.customerService.changePwd(mango3.loginedCustomer.getLoginId(), newPwd);
							else System.out.println("( つ｡>﹏<｡)つ PW가 일치하지 않습니다.");
						} else System.out.println("( つ｡>﹏<｡)つ PW가 올바르지 않습니다.");

					} else if (input.equals("4")){
						mango3.logout();
						break;
					} else if (input.equals("0")) {
						
						System.out.print("PW 확인 : ");
						String pwd=sc.nextLine();
						if(mango3.loginedCustomer.getPwd().equals(pwd)) {
							System.out.println("( つ｡>﹏<｡)つ 회원 탈퇴를 진행하시겠습니까?");
							System.out.println("탈퇴 시 계정은 복구되지 않습니다. (0 입력 시 회원 탈퇴 진행)");
							System.out.print("✔️ ");
							String fin=sc.nextLine();
							if(fin.equals("0")) {
								mango3.customerService.removeCustomer(mango3.loginedCustomer.getLoginId());
								mango3.loginedCustomer=null;
								System.out.println("༼ つ ◕_◕ ༽つ 로그인 화면으로 돌아갑니다.");
								break;
							} else System.out.println("༼ つ ◕_◕ ༽つ 회원 탈퇴를 취소합니다.");
						} else System.out.println("( つ｡>﹏<｡)つ 비밀번호가 올바르지 않습니다.");
						
					} else System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다.");

				}
			}
		}

	}	
}
