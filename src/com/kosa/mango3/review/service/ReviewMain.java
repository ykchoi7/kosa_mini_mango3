package com.kosa.mango3.review.service;

import java.time.LocalDate;
import java.util.List;

import com.kosa.mango3.customer.CustomerDTO;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.dao.ReviewDAO;
import com.kosa.mango3.review.dao.ReviewDAOOracle;
import com.kosa.mango3.review.dto.ReviewDTO;
import com.kosa.mango3.store.StoreDTO;


public class ReviewMain {	
	java.util.Scanner sc = new java.util.Scanner(System.in);
	ReviewDAOOracle rDAO = new ReviewDAOOracle();
	
	List<ReviewDTO> reviewList;
	String loginId;
	Long storeId;

	public void addMenu(long storeId, String loginedId) {
		System.out.println(">>리뷰 추가<<");
		System.out.println("평점을 번호로 골라주세요");
		System.out.println("1. 맛있다");
		System.out.println("2. 괜찮다");
		System.out.println("3. 별로");
		System.out.print(">입력:");
		String grade = sc.nextLine();
		System.out.println("리뷰 입력하세요");
		System.out.print(">내용 입력:");
		String comment = sc.nextLine();			
		LocalDate today = LocalDate.now();
		
		System.out.println("'--------------------------------------------");	
		System.out.println("1." + loginId + " - " + grade);
		System.out.println("ㄴ " + comment);
		System.out.println("                               " + today);
		System.out.print("등록 하시겠습니까?(y/n)");
		String yn = sc.nextLine();
		
		if(yn.equals("n")) {
			return;
		}
		
		ReviewDTO reviewDTO = ReviewDTO.builder()
							.grade(Integer.parseInt(grade))
							.comment(comment)
							.customerDTO(CustomerDTO.builder().loginId(loginId).build())
							.storeDTO(StoreDTO.builder().storeId(storeId).build())
							.build();	
		rDAO.create(reviewDTO);
	}
	private void showReviewsByGrade(long storeId, int grade) {
		try {
			reviewList = rDAO.selectByGrade(storeId, grade);
			if(reviewList.size() == 0) {
				printFail("리뷰가 없습니다"); 
			} else {
				printSuccess(reviewList);
			}
		} catch (FindException e) {
			printFail("리뷰 보기 실패");
		}
	}
	
	public void showReivewsMenu(long storeId) {		
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
					reviewList = rDAO.selectByStoreNo(storeId);
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
				break;
			}
		}
	}
	private void printSuccess(ReviewDTO reviewDTO) {
		String stringGrade = stringGrade(reviewDTO.getGrade());
		System.out.println("'--------------------------------------------");	
		System.out.println("1. " + reviewDTO.getCustomerDTO().getLoginId() + " - " + stringGrade);
		System.out.println("ㄴ " + reviewDTO.getComment());
		System.out.println("                               " + reviewDTO.getRegdate());
		
	}
	
	private void printSuccess(List<ReviewDTO> reviewList) {
		for(int i = 0; i<reviewList.size(); i++) {
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
		while(true) {
			System.out.println();
			System.out.println("1. 리뷰보기");
			System.out.println("2. 리뷰작성");
			System.out.println("0. 뒤로가기");
			System.out.println("*. 홈");
			System.out.print(">입력:");
			String input = sc.next();		
			
			switch(Integer.parseInt(input)) {
				case 1 :
					showReivewsMenu(storeId);
					break;
				case 2 :
					addMenu(storeId, loginedId);
					break;
				case 0 :
					break;
				case '*' :
					break;
			}	
		}
	}

	public static void main(String[] args) {
		ReviewMain reviewMain = new ReviewMain();
		long storeId = 1;
		String loginedId = "test1";
		reviewMain.reviewMenu(storeId, loginedId);
		
	}
}
