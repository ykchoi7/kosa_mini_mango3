package com.kosa.mango3.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.ReviewMain;
import com.kosa.mango3.store.dao.StoreDAO;
import com.kosa.mango3.store.dao.StoreDAOOracle;
import com.kosa.mango3.store.dto.StoreDTO;
import com.kosa.mango3.store.service.StoreService;

public class StoreMain {
	private StoreDAO dao; 
	private StoreService storeService;
	private ReviewMain reviewMain;

	public StoreMain() {
		this.dao = new StoreDAOOracle();
		this.storeService = new StoreService();
		this.reviewMain = new ReviewMain();
	}
	
	//음식조회 카테고리
	public void serviceStore(String loginedId) {
		Scanner sc = new Scanner(System.in);
		String num = "";

		while(true) { 
			System.out.println("=".repeat(30));
			System.out.println("1.지역 조회");
			System.out.println("2.음식종류 조회");
			System.out.println("3.음식점 검색");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));

			//번호 입력받기
			System.out.println("이동하고 싶은 메뉴 번호를 입력해주세요");
			System.out.print("입력>> ");
			num = sc.nextLine();

			//번호 입력받았을 때 이동
			if (num.equals("1")) {
				serviceLoc(loginedId);
			} else if (num.equals("2")) {
				serviceType(loginedId);
			} else if (num.equals("3")) {
				serviceSearch(loginedId);
			} else if (num.equals("0")) {
				return;
			} else {
				System.out.println("없는 번호입니다. 번호를 다시 입력해주세요");
				return;
			}
		}
	}

	//위치에 따른 가게 정보 조회
	public void serviceLoc(String loginedId) {
		Scanner sc = new Scanner(System.in);
		List<StoreDTO> storeList = new ArrayList<>();
		String lnum = "";
				
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.강남");
			System.out.println("2.성수");
			System.out.println("3.잠실");
			System.out.println("4.마포");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));

			//지역번호 입력받기
			System.out.println("조회하고 싶은 장소 번호를 입력해주세요");
			System.out.print("입력>> ");
			lnum = sc.nextLine();

			if (lnum.equals("0")) return;
			String[] location = {"강남", "성수", "잠실", "마포"};
			
			int page = 1;
			int max=0;
			int maxPage=0;
			int finalSize = 5;
			int size=5;
			
			while(true) {				
				int tmp=-1;
				try {
					tmp = storeService.cntStoreLoc(location[(Integer.parseInt(lnum))-1]);
					if (tmp == 0) {
						System.out.println("[알림]등록된 리뷰가 없습니다.");
						return;
					}
				} catch (FindException e) {
					System.out.println(e.getMessage());
				}
				if(max!=tmp) {
					max=tmp;
					maxPage = max%5==0 ? max/5 : max/5+1;
				}
								
				try {
					storeList = storeService.serviceLoc(location[(Integer.parseInt(lnum))-1], page);
				} catch (NumberFormatException | FindException e) {
					System.out.println(e.getMessage());
				}				
				
				size = (page == maxPage) ? max%size : finalSize;
				int idx = finalSize*(page-1)+1;

				//가게 리스트 정보 출력
				storePrint(storeList, idx);

				//가게 상세 정보
				System.out.println();
				if (page!=1) System.out.println("p.이전 리스트 <-----");
				if (page<maxPage) System.out.println("-----> n.다음 리스트");
				System.out.println("0.뒤로가기");
				System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
				System.out.print("입력>> ");

				String sdetail = sc.nextLine();
				int num=0;

				if (sdetail.equals("p")) {
					if(page>1) page--;
					else System.out.println("첫번째 페이지입니다.");
				} else if (sdetail.equals("n")) {
					if(page<max) page++;
					else System.out.println("마지막 페이지입니다.");
				} else if (sdetail.equals("0")) {
					break;
				} else {
					try {
						num = (Integer.parseInt(sdetail))-1;
						//if(!(num==1 || num==2 || num==3 || num==4)) throw new Exception("오류");
						storeInfo(storeList, num%5, loginedId);
					} catch (Exception e) {
						System.out.println("오류");
					}
				}
				
			}
				
			
		}
	}


	//음식종류별 가게 조회
	public void serviceType(String loginedId) {
		List<StoreDTO> storeList = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String tnum = "";
		
		while(true) {
			System.out.println("=".repeat(30));
			System.out.println("1.한식");
			System.out.println("2.중식");
			System.out.println("3.일식");
			System.out.println("4.양식");
			System.out.println("0.뒤로 가기");
			System.out.println("-".repeat(30));

			//지역번호 입력받기
			System.out.println("조회하고 싶은 음식 메뉴 번호를 입력해주세요");
			System.out.print("입력>> ");
			tnum = sc.nextLine();

			if (tnum.equals("0")) return;
			String[] type = {"한식", "중식", "일식", "양식"};
			
			int page = 1;
			int max=0;
			int maxPage=0;
			int finalSize = 5;
			int size=5;
			
			while(true) {
				int tmp=-1;
				try {
					tmp = storeService.cntStoreType(type[(Integer.parseInt(tnum))-1]);
					if (tmp == 0) {
						System.out.println("[알림]등록된 리뷰가 없습니다.");
						return;
					}
				} catch (FindException e) {
					System.out.println(e.getMessage());
				}
				if(max!=tmp) {
					max=tmp;
					maxPage = max%5==0 ? max/5 : max/5+1;
				}
				
				try {
					storeList = storeService.serviceType(type[(Integer.parseInt(tnum))-1], page);
				} catch (NumberFormatException | FindException e) {
					System.out.println(e.getMessage());
				}				
				
				size = (page == maxPage) ? max%size : finalSize;
				int idx = finalSize*(page-1)+1;

				//가게 리스트 정보 출력
				storePrint(storeList, idx);

				//번호입력받아서 가게 상세정보 출력하기
				System.out.println();
				if (page!=1) System.out.println("p.이전 리스트 <-----");
				if (page<maxPage) System.out.println("-----> n.다음 리스트");
				System.out.println("0.뒤로가기");
				System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
				System.out.print("입력>> ");

				String tdetail = sc.nextLine();
				int num=0;

				if (tdetail.equals("p")) {
					if(page>1) page--;
					else System.out.println("첫번째 페이지입니다.");
				} else if (tdetail.equals("n")) {
					if(page<max) page++;
					else System.out.println("마지막 페이지입니다.");
				} else if (tdetail.equals("0")) {
					break;
				} else {
					try {
						num = (Integer.parseInt(tdetail))-1;
//						if(!(num==1 || num==2 || num==3 || num==4)) throw new Exception("오류");
						storeInfo(storeList, num%5, loginedId);
					} catch (Exception e) {
						System.out.println("오류");
					}
				}
				
			}
			
		}
	}

	//가게명으로 검색
	public void serviceSearch(String loginedId){
		List<StoreDTO> storeList = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String storeName;

		System.out.println("검색할 음식점 이름을 입력해주세요");
		System.out.print("입력>> ");
		storeName = sc.nextLine();
		
		int page = 1;
		int max=0;
		int maxPage=0;
		int finalSize = 5;
		int size=5;
		
		while(true) {
			int tmp=-1;
			try {
				tmp = storeService.cntStoreSearch(storeName);
				if (tmp == 0) {
					System.out.println("[알림]등록된 리뷰가 없습니다.");
					return;
				}
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}
			if(max!=tmp) {
				max=tmp;
				maxPage = max%5==0 ? max/5 : max/5+1;
			}
			
			try {
				storeList = storeService.serviceSearch(storeName, page);
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}

			size = (page == maxPage) ? max%size : finalSize;
			int idx = finalSize*(page-1)+1;
		
			storePrint(storeList, idx);
				

			//번호입력받아서 가게 상세정보 출력하기
			System.out.println();
			if (page!=1) System.out.println("p.이전 리스트 <-----");
			if (page<maxPage) System.out.println("-----> n.다음 리스트");
			System.out.println("0.뒤로가기");
			System.out.println("상세정보를 보고 싶은 가게번호를 입력해주세요");
			System.out.print("입력>> ");

			String detail = sc.nextLine();
			int num=0;
			
			if (detail.equals("p")) {
				if(page>1) page--;
				else System.out.println("첫번째 페이지입니다.");
			} else if (detail.equals("n")) {
				if(page<max) page++;
				else System.out.println("마지막 페이지입니다.");
			} else if (detail.equals("0")) {
				break;
			} else {
				try {
					num = (Integer.parseInt(detail))-1;
					//if(!(num==1 || num==2 || num==3 || num==4)) throw new Exception("오류");
					storeInfo(storeList, num%5, loginedId);
				} catch (Exception e) {
					System.out.println("오류");
				}
			}
			
		}
	}
	
	private void storePrint(List<StoreDTO> storeList, int idx) {
		int end = 5; //리뷰 별 갯수 크기 지정
		
		for (int i = 0; i < storeList.size(); i++) {
			String name = storeList.get(i).getStoreName();
			int star = (int)storeList.get(i).getGrade();
			
			System.out.println("---------------------------");
			System.out.print(idx+i + ". " + name + " - ");
			System.out.print("★".repeat(star) + "☆".repeat(end - star));
			System.out.println(" (" + storeList.get(i).getReviewId() +")");
			System.out.println("위치 : " + storeList.get(i).getLocation());
			System.out.println("음식 종류 : " + storeList.get(i).getFoodType());					
		}
	}
	
	private void storeInfo(List<StoreDTO> storeList, int num, String loginedId) {
		int end = 5; //리뷰 별 갯수 크기 지정
		int star = (int)storeList.get(num).getGrade();
		
		System.out.println("---------------------------");
		System.out.print(storeList.get(num).getStoreName() + " - ");
		System.out.print("★".repeat(star) + "☆".repeat(end - star));
		System.out.println(" (" + storeList.get(num).getReviewId() + ")");
		System.out.println("주소 : " + storeList.get(num).getAddress());
		System.out.println("연락처   : " + storeList.get(num).getTel());
		System.out.println("음식 종류 : " + storeList.get(num).getFoodType());
		System.out.println("영업시간 : " + storeList.get(num).getStoreHour());

		reviewMain.reviewMenu(storeList.get(num).getStoreId(), loginedId);
	}
}
