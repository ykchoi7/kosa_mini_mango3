package com.kosa.mango3.review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.RemoveException;
import com.kosa.mango3.review.dto.ReviewDTO;
import com.kosa.mango3.review.service.ReviewService;
import com.kosa.mango3.store.dto.StoreDTO;

public class ReviewMain {	
	private ReviewService reviewService;
	
	public ReviewMain() {
		this.reviewService = new ReviewService();
	}
	
	public void addMenu(long storeId, String loginedId) {
		Scanner sc = new Scanner(System.in);
				
		System.out.println("༼ つ ◕_◕ ༽つ 리뷰를 등록합니다.");
		System.out.println("음식 후기를 골라주세요.");
		System.out.println("1. 맛있다(^_^)b");
		System.out.println("2. 괜찮다(^^);");
		System.out.println("3. 별로(-_-);");
		System.out.print("✔️ ");
		String input = sc.nextLine();
		int grade=0;
		if(input.equals("1")) {
			grade=5;
			input="맛있다(^_^)b";
		} else if(input.equals("2")) {
			grade=3;
			input="괜찮다(^^);";
		} else if(input.equals("3")) {
			grade=1;
			input="별로(-_-);";
		} else {
			System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다.");
			return;
		}
		
		System.out.println("༼ つ ◕_◕ ༽つ 리뷰 내용을 입력하세요");
		System.out.print("✔️ ");
		String comment = sc.nextLine();			
		LocalDate today = LocalDate.now();
		
//		System.out.println("1." + loginedId + " - " + input);
//		System.out.println("ㄴ " + comment);
//		System.out.println("                               " + today);
		
		System.out.println("-".repeat(30));
		reviewPrint(1, loginedId, input, comment, today.toString());
		
		System.out.println("༼ つ ◕_◕ ༽つ 리뷰를 등록하시겠습니까? (0 입력 시 리뷰 등록 취소)");
		System.out.print("✔️ ");
		String yn = sc.nextLine();
		
		if(yn.equals("0")) {
			return;
		}
		
		ReviewDTO reviewDTO = ReviewDTO.builder()
							.grade(grade)
							.comment(comment)
							.customerDTO(CustomerDTO.builder().loginId(loginedId).build())
							.storeDTO(StoreDTO.builder().storeId(storeId).build())
							.build();	
		
		try {
			reviewService.create(reviewDTO);
		} catch (AddException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void showReviewsByStore(long storeId) {
		Scanner sc = new Scanner(System.in);
		
		int page = 1;
		int max = 0;
		int maxPage = 0;
		int finalSize = 5;
		int size = 5;

		while (true) {
			int tmp = -1;
			
			try {
				tmp = reviewService.countStoreReview(storeId);
				if (tmp == 0) {
					System.out.println("༼ つ ◕_◕ ༽つ 등록된 리뷰가 없습니다.");
					return;
				}
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}
			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
			
			try {
				reviewList = reviewService.selectByStoreNo(storeId, page);
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}

			if(max != tmp) {
				max = tmp;
				maxPage = max%5==0 ? max/5 : max/5+1;
			}
			size = (page == maxPage) ? max%size : finalSize; 
			
			System.out.println("-".repeat(30));
			int idx = finalSize*(page-1)+1;
			
			for (int i = 0; i<size; i++) {
				storeReviewPrint(reviewList.get(i), idx+i);
			}

			if (page != 1) System.out.print("(p) 이전 리스트 <-- ");
			if(reviewList.size()!=0) System.out.print("("+page+"/"+maxPage+")");

			if (page<maxPage) System.out.println(" --> 다음 리스트 (n)");
			else System.out.println();
			

			System.out.println("0. 뒤로가기");
			System.out.print("✔️ ");
			String input = sc.nextLine();

			if (input.equals("p")) {
				if(page > 1) page--;
				else System.out.println("༼ つ ◕_◕ ༽つ 첫번째 페이지입니다.");
			} else if (input.equals("n")) {
				if(page < max) page++;
				else System.out.println("༼ つ ◕_◕ ༽つ 마지막 페이지입니다.");
			} else if (input.equals("0")) {
				break;
			} else {
				System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다. 다시 입력해 주세요.");
			}
		}
	}
	
	private void showReviewsByGrade(long storeId, int grade) {
		Scanner sc = new Scanner(System.in);
		
		int page = 1;
		int max = 0;
		int maxPage = 0;
		int finalSize = 5;
		int size = 5;

		while (true) {
			int tmp = -1;
			
			try {
				tmp = reviewService.countGradeReview(storeId, grade);
				if (tmp == 0) {
					System.out.println("༼ つ ◕_◕ ༽つ 등록된 리뷰가 없습니다.");
					return;
				}
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}
			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
			
			try {
				reviewList = reviewService.selectByGrade(storeId, grade, page);
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}

			if(max != tmp) {
				max = tmp;
				maxPage = max%5==0 ? max/5 : max/5+1;
			}
			size = (page == maxPage) ? max%size : finalSize; 
			
			System.out.println("-".repeat(30));
			int idx = finalSize*(page-1)+1;
			
			for (int i = 0; i<size; i++) {
				storeReviewPrint(reviewList.get(i), idx+i);
			}

			if (page != 1) System.out.print("(p) 이전 리스트 <-- ");
			if(reviewList.size()!=0) System.out.print("("+page+"/"+maxPage+")");
			if (page < maxPage) System.out.println(" --> 다음 리스트 (n)");
			else System.out.println();
			
			System.out.println("0. 뒤로가기");
			System.out.print("✔️ ");
			String input = sc.nextLine();

			if (input.equals("p")) {
				if(page > 1) page--;
				else System.out.println("༼ つ ◕_◕ ༽つ 첫번째 페이지입니다.");
			} else if (input.equals("n")) {
				if(page < max) page++;
				else System.out.println("༼ つ ◕_◕ ༽つ 마지막 페이지입니다.");
			} else if (input.equals("0")) {
				break;
			} else {
				System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다. 다시 입력해 주세요.");
			}
		}
	}
	
	public void showReviewsMenu(long storeId, String loginedId) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println();
			System.out.println("༼ つ ◕_◕ ༽つ 원하시는 서비스를 입력하세요.");
			System.out.println("1. 전체 리뷰");
			System.out.println("2. '맛있다' 리뷰");
			System.out.println("3. '괜찮다' 리뷰");
			System.out.println("4. '별로' 리뷰");
			System.out.println("0. 뒤로 가기");
			System.out.print("✔️ ");
			String input = sc.nextLine();
			
			switch(Integer.parseInt(input)) {
			case 1 :
				showReviewsByStore(storeId);
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
				return;
			}
		}
	}
	
	public void reviewMenu(Long storeId, String loginedId) {
		Scanner sc = new Scanner(System.in);
				
		while(true) {
			System.out.println("༼ つ ◕_◕ ༽つ 원하시는 서비스를 입력하세요.");
			System.out.println("1. 리뷰 조회");
			System.out.println("2. 리뷰 등록");
			System.out.println("0. 뒤로가기");
			System.out.print("✔️ ");
			String input = sc.nextLine();		
			
			switch(input) {
				case "1" :
					showReviewsMenu(storeId, loginedId);
					break;
				case "2" :
					addMenu(storeId, loginedId);
					break;
				case "0" :
					return;
				default :
					System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다.");
					break;
			}	
		}
	}
	
	public void myReviewList(String loginId) {
		Scanner sc = new Scanner(System.in);
		
		int page = 1;
		int max = 0;
		int maxPage = 0;
		int finalSize = 5;
		int size = 5;

		while (true) {
			int tmp = -1;
			
			try {
				tmp = reviewService.countMyReview(loginId);
				if (tmp == 0) {
					System.out.println("༼ つ ◕_◕ ༽つ 등록된 리뷰가 없습니다.");
					return;
				}
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}
			
			List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();;
			
			try {
				reviewList = reviewService.selectByCustomer(loginId, page);
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}
			
			if(max != tmp) {
				max = tmp;
				maxPage = max%5==0 ? max/5 : max/5+1;
			}
			size = (page == maxPage) ? max%size : finalSize; 
				
			System.out.println("-".repeat(30));
			int idx = finalSize*(page-1)+1;
			
			for (int i = 0; i<size; i++) {
				myReviewPrint(reviewList.get(i), idx+i);
			}
			
			if (page!=1) System.out.print("(p) 이전 리스트 <-- ");
			if(reviewList.size()!=0) System.out.print("("+page+"/"+maxPage+")");
			if (page<maxPage) System.out.println(" --> 다음 리스트 (n)");
			else System.out.println();
				
			System.out.println("0. 뒤로가기");
			System.out.println("*. 리뷰 삭제");
			System.out.print("✔️ ");
			String input = sc.nextLine();

			if (input.equals("p")) {
				if(page>1) page--;
				else System.out.println("༼ つ ◕_◕ ༽つ 첫번째 페이지입니다.");
			} else if (input.equals("n")) {
				if(page<max) page++;
				else System.out.println("༼ つ ◕_◕ ༽つ 마지막 페이지입니다.");
			} else if (input.equals("0")) {
				break;
			} else if (input.equals("*")) {
				myReviewDelete(reviewList);
			} else {
				System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다. 다시 입력해 주세요.");
			}
		}
	}
	
	private void myReviewDelete(List<ReviewDTO> reviewList) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("༼ つ ◕_◕ ༽つ 몇 번 리뷰를 삭제하시겠습니까?");
		System.out.print("✔️ ");
		String input = sc.nextLine();
		int index = 0;
		
		try {
			index = Integer.parseInt(input)%6-1;
		} catch (Exception e) {
			System.out.println("( つ｡>﹏<｡)つ 잘못 입력하였습니다.");
			return ;
		}
		
		System.out.println("-".repeat(30));
		
		try {
			myReviewPrint(reviewList.get(index), Integer.parseInt(input)-1);
		} catch (Exception e) {
			System.out.println("( つ｡>﹏<｡)つ 입력한 번호의 리뷰를 조회할 수 없습니다.");
			return ;
		}

		System.out.println("༼ つ ◕_◕ ༽つ 위 리뷰를 삭제 하시겠습니까? (0 입력 시 리뷰 삭제 취소)");
		System.out.print("✔️ ");
		input = sc.nextLine();

		if (!input.equals("0")) {
			Long reviewId = reviewList.get(index).getReviewId();
			try {
				reviewService.delete(reviewId);
			} catch (RemoveException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("༼ つ ◕_◕ ༽つ 리뷰 삭제가 완료되었습니다.");
		} 
	}
	
	private void myReviewPrint(ReviewDTO reviewDTO, int idx) {
		String storeName = reviewDTO.getStoreDTO().getStoreName();
		String grade = getGrade(reviewDTO.getGrade());
		String content = reviewDTO.getComment();
		String date = reviewDTO.getRegdate();

		reviewPrint(idx, storeName, grade, content, date);
	}
	
	private void storeReviewPrint(ReviewDTO reviewDTO, int idx) {
		String loginid = reviewDTO.getCustomerDTO().getLoginId();
		String grade = getGrade(reviewDTO.getGrade());
		String content = reviewDTO.getComment();
		String date = reviewDTO.getRegdate();

		reviewPrint(idx, loginid, grade, content, date);
	}
	
	private String getGrade(int grade) {
		if (grade == 5) {
			return "맛있다(^_^)b";
		} else if (grade == 3) {
			return "괜찮다(^^);";
		} else {
			return "별로(-_-);";
		}
	}
	
	private void reviewPrint(int idx, String ...reviewInfo) {
		System.out.printf("%d. " + reviewInfo[0] + "\t   " + reviewInfo[1] + "\n", idx);
		System.out.println("ㄴ" + reviewInfo[2]);
		System.out.println("\t\t   " + reviewInfo[3]);
		System.out.println("-".repeat(30));
	}
}
