package com.kosa.mango3.review;

import java.util.Scanner;

import com.kosa.mango3.db.Oracle;

public class ReviewService {
	static ReviewDAO rwd = new ReviewDAO();
	
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int num = in.nextInt();
		

//		rwd.printReviews(1);
		rwd.selectByGrade(1,5);
		
//		while(true) {
//			switch(num) {
//				case 1 :
//					System.out.println("리뷰보기");
//					rwd.selectByStore(1);
//					break;
//				case 2 :
//					System.out.println("리뷰작성");
//					rwd.selectByStore(1);
//					break;
//				case 0 :
//					System.out.println("뒤로가기");
//					break;
//				case 00 :
//					System.out.println("홈");
//					break;
//			}
//			
//		}

	}
}
