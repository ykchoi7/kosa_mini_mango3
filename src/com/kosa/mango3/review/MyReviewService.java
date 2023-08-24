package com.kosa.mango3.review;

import java.util.List;
import java.util.Scanner;

public class MyReviewService {
	private ReviewDAO r;
	private Scanner sc = new Scanner(System.in);
	
	public void menu(String loginId) {
		List<ReviewDTO> reviewList = r.selectByCustomer(loginId);
		
		int size = 5;
		int start = 0;
		int end = size;
		
		while (true) {
			for (int i = start; i<end; i++) {
				String storeName = reviewList.get(i).getStoreDTO().getStoreName();
				String grade;
				
				if (reviewList.get(i).getGrade() == 5) {
					grade = "맛있다.";
				} else if (reviewList.get(i).getGrade() == 3) {
					grade = "괜찮다.";
				} else {
					grade = "별로 -_-";
				}
				
				String content = reviewList.get(i).getComment();
				String date = reviewList.get(i).getRegdate();
				
				System.out.println("-".repeat(30));
				System.out.printf("%d. " + storeName + " - " + grade + "\n", i+1);
				System.out.println("ㄴ" + content);
				System.out.println("\t\t\t\t" + date);
				System.out.println("-".repeat(30));
			}
				if (start != 0) System.out.println("p. 이전 리스트");
				if (reviewList.size() > end) System.out.println("n. 다음 리스트");
				System.out.println("0. 뒤로가기");
				System.out.println("*. 리뷰 삭제하기");
				System.out.print(">> ");
				
				String input = sc.nextLine();
			
				if (input.equals("p")) {
					start -= size;
					end -= size;
				} else if (input.equals("n")) {
					start += size;
					end += size;
				} else if (input.equals("0")) {
					sc.close();
					break;
				} else if (input.equals("*")) {
					myReviewDelete(reviewList);
				} else {
					System.out.println("[안내] 잘못 입력하셨습니다, 다시 입력해 주세요.");
				}
		}
	}
	
	private void myReviewDelete(List<ReviewDTO> reviewList) {

		System.out.println("[안내] 몇 번 리뷰를 삭제하시겠습니까?");
		System.out.print(">> ");
		
		String input = sc.nextLine();
		for (char ch : input.toCharArray()) {
			if (!Character.isDigit(ch)) {
				System.out.println("[안내] 잘못 입력하셨습니다, 다시 입력해 주세요.");
				return;
			}
		}
			
		Long reviewId = reviewList.get(Integer.parseInt(input)-1).getReviewId();
		r.delete(reviewId);
		
		System.out.println("[안내] 삭제가 완료되었습니다.");
	}
}
