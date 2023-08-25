package com.kosa.mango3.review;

import java.util.List;
import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyReviewService {
	private ReviewDAO reviewDAO;
	
	public void myReviewList(String loginId) {
		Scanner sc = new Scanner(System.in);
		List<ReviewDTO> reviewList = reviewDAO.selectByCustomer(loginId);
		
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
		String date = reviewList.get(i).getRegdate();
		
		System.out.printf("%d. " + storeName + "\t   " + grade + "\n", i+1);
		System.out.println("ㄴ" + content);
		System.out.println("\t\t   " + date);
		System.out.println("-".repeat(30));
	}
}
