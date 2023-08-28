package com.kosa.mango3.review.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.Mango3;
import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.dao.ReviewDAOOracle;
import com.kosa.mango3.review.dto.ReviewDTO;
import com.kosa.mango3.store.Mango3Store;
import com.kosa.mango3.store.dto.StoreDTO;

public class ReviewMain {	
	
	java.util.Scanner sc = new java.util.Scanner(System.in);
	ReviewDAOOracle reviewDAO = new ReviewDAOOracle();
//	Mango3Store mango3store = new Mango3Store();
	
	List<ReviewDTO> reviewList;
	
//	public static void main(String[] args) {
//		ReviewMain reviewMain = new ReviewMain();
//		long storeId = 1;
//		String loginedId = "test1";
//		reviewMain.reviewMenu(storeId, loginedId);
//	}

	public void addMenu(long storeId, String loginedId) {
		
		System.out.println(">>리뷰 추가<<");
		System.out.println("평점을 번호로 골라주세요");
		System.out.println("1. 맛있다");
		System.out.println("2. 괜찮다");
		System.out.println("3. 별로");
		System.out.print(">입력:");
		String grade = sc.next();
		
		System.out.println("리뷰 입력하세요");
		System.out.print(">내용 입력:");
		String comment = sc.next();			
		LocalDate today = LocalDate.now();
		
		System.out.println("'--------------------------------------------");	
		System.out.println("1." + loginedId + " - " + grade);
		System.out.println("ㄴ " + comment);
		System.out.println("                               " + today);
		System.out.print("등록 하시겠습니까?(y/n)");
		String yn = sc.next();
		
		if(yn.equals("n")) {
			return;
		}
		
		ReviewDTO reviewDTO = ReviewDTO.builder()
							.grade(Integer.parseInt(grade))
							.comment(comment)
							.customerDTO(CustomerDTO.builder().loginId(loginedId).build())
							.storeDTO(StoreDTO.builder().storeId(storeId).build())
							.build();	
		reviewDAO.create(reviewDTO, loginedId);
	}
	
	private void showReviewsByGrade(long storeId, int grade) {
		try {
			reviewList = reviewDAO.selectByGrade(storeId, grade);
			if(reviewList.size() == 0) {
				printFail("리뷰가 없습니다"); 
			} else {
				printSuccess(reviewList);
			}
		} catch (FindException e) {
			printFail("리뷰 보기 실패");
		}
	}
	
	public void showReviewsMenu(long storeId, String loginedId) {
		
		while(true) {
			System.out.println();
			System.out.println(">>리뷰 보기<<");
			System.out.println("1. 전체 리뷰 보기");
			System.out.println("2. 평점이 '맛있다'인 리뷰만 보기");
			System.out.println("3. 평점이 '괜찮다'인 리뷰만 보기");
			System.out.println("4. 평점이 '별로'인 리뷰만 보기");
			System.out.println("0. 뒤로 가기");
			System.out.print(">입력:");
			String input = sc.next();
			
			switch(Integer.parseInt(input)) {
			case 1 :
				try {
					reviewList = reviewDAO.selectByStoreNo(storeId);
					if(reviewList.size() == 0) {
						printFail("리뷰가 없습니다"); 
					} else {
						printSuccess(reviewList);
					}
				} catch (FindException e) {					
					printFail("전체 리뷰 보기 실패");
				}
				
				break;
			case 2 :
				showReviewsByGrade(storeId, 5);
				break;
			case 3:
				showReviewsByGrade(storeId, 3);
				break;
			case 4:
				showReviewsByGrade(storeId, 1);
				break;
			case 0 :
				reviewMenu(storeId, loginedId);
				break;
			}
		}
	}
	private void printSuccess(ReviewDTO reviewDTO) {
		String stringGrade = stringGrade(reviewDTO.getGrade());
			
		System.out.println(reviewDTO.getCustomerDTO().getLoginId() + " - " + stringGrade);
		System.out.println("ㄴ " + reviewDTO.getComment());
		System.out.println("                               " + reviewDTO.getRegdate());
		
	}
	
	private void printSuccess(List<ReviewDTO> reviewList) {
		for(int i = 0; i<reviewList.size(); i++) {
			System.out.print("'--------------------------------------------\n"+(i+1)+". ");
			ReviewDTO d = reviewList.get(i);
			printSuccess(d);
		}
	}
	
	
	void printFail(String msg) {
		System.out.println(msg);
	}
	String stringGrade(int grade) {
		String stringGrade = null;
		switch(grade) {
			case 5 :
				stringGrade = "맛있다";
				break;
			case 3 :
				stringGrade = "괜찮다";
				break;
			case 1 :
				stringGrade = "별로";
				break;
		}
		return stringGrade;
	}
	
	public void reviewMenu(Long storeId, String loginedId) {
		Mango3Store mango3store = new Mango3Store();
//		Mango3 mango3 = new Mango3();
		
		while(true) {
			System.out.println();
			System.out.println("1. 리뷰보기");
			System.out.println("2. 리뷰작성");
			System.out.println("0. 뒤로가기");
//			System.out.println("*. 홈");
			System.out.print(">입력:");
			String input = sc.next();		
			
			switch(input) {
				case "1" :
					showReviewsMenu(storeId, loginedId);
					break;
				case "2" :
					addMenu(storeId, loginedId);
					break;
				case "0" :
					mango3store.serviceLoc("input", loginedId);
					return;
//				case "*" :
//					mango3.home();
//					break;
			}	
		}
	}

	
	/*
	public void myReviewList(String loginId) {
		Scanner sc = new Scanner(System.in);
		List<ReviewDTO> reviewList = reviewDAO.selectByCustomer(loginId);
		*/
	
	public void myReviewList(String loginId, int page) {
		Scanner sc = new Scanner(System.in);
		List<ReviewDTO> reviewList = reviewDAO.selectByCustomer(loginId, page);
		
		int size = 5;
		int start = 0;
		int end = reviewList.size() < size ? reviewList.size() : size;
		
		while (true) {
			if (reviewList.size() == 0) {
				System.out.println("[알림]등록된 리뷰가 없습니다.");
				return;
			}
			
			System.out.println("-".repeat(30));
			for (int i = start; i<end; i++) {
				reviewPrint(reviewList, i);
			}
			
			if (start != 0) System.out.println("p.이전 리스트");
			if (reviewList.size() > end) System.out.println("n.다음 리스트");
			System.out.println("0.뒤로가기");
			System.out.println("*.리뷰 삭제하기");
			System.out.print(">> ");
			String input = sc.nextLine();
		
			if (input.equals("p")) {
				start -= size;
				end = end%size == 0 ? end - size : end - (end%size);
			} else if (input.equals("n")) {
				start += size;
				end = reviewList.size() < end + size ? reviewList.size() : end + size;
			} else if (input.equals("0")) {
				break;
			} else if (input.equals("*")) {
				int beforeSize = reviewList.size();
				reviewList = myReviewDelete(reviewList, sc);
				int afterSize = reviewList.size(); 
			
				if (beforeSize != afterSize) {
					end--;
				} 
			} else {
				System.out.println("[알림]잘못 입력하였습니다, 다시 입력해 주세요.");
			}
		}
	}
	
	private List<ReviewDTO> myReviewDelete(List<ReviewDTO> reviewList, Scanner sc) {
		System.out.println("[알림]몇 번 리뷰를 삭제하시겠습니까?");
		System.out.print(">> ");
		String input = sc.nextLine();
		
		for (char ch : input.toCharArray()) {
			if (!Character.isDigit(ch)) {
				System.out.println("[알림]잘못 입력하였습니다.");
				return reviewList;
			}
		}
		
		if (input.equals("0") || reviewList.size() < Integer.parseInt(input)) {
			System.out.println("[알림]입력한 번호의 리뷰를 조회할 수 없습니다.");
			return reviewList;
		}
				
		int index = Integer.parseInt(input)-1;
		
		System.out.println("-".repeat(30));
		reviewPrint(reviewList, index);

		System.out.println("[알림]위 리뷰를 삭제 하시겠습니까?(y/n)");
		System.out.print(">> ");
		input = sc.nextLine();
		
		if (input.equals("y")) {
			Long reviewId = reviewList.get(index).getReviewId();
			reviewDAO.delete(reviewId);
			reviewList.remove(index);
			
			System.out.println("[알림]삭제가 완료되었습니다.");
		} else {
			System.out.println("[알림]잘못 입력하였습니다.");
		}
		
		return reviewList;
	}
	
	private void reviewPrint(List<ReviewDTO> reviewList, int i) {
		String storeName = reviewList.get(i).getStoreDTO().getStoreName();
		String grade;
		
		if (reviewList.get(i).getGrade() == 5) {
			grade = "맛있다(^_^)b";
		} else if (reviewList.get(i).getGrade() == 3) {
			grade = "괜찮다(^^);";
		} else {
			grade = "별로(-_-);";
		}
		
		String content = reviewList.get(i).getComment();
		Date date = reviewList.get(i).getRegdate();
//		String date = reviewList.get(i).getRegdate();
		
		System.out.printf("%d. " + storeName + "\t   " + grade + "\n", i+1);
		System.out.println("ㄴ" + content);
		System.out.println("\t\t   " + date);
		System.out.println("-".repeat(30));
	}
}
