package com.kosa.mango3.store.admin;

import java.util.List;
import java.util.Scanner;

import com.kosa.mango3.store.StoreDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdminStoreService {
	private AdminStoreDAO adminStoreDAO; 
	
	public void management() {
		Scanner sc = new Scanner(System.in);
		String input = "";
		System.out.println("[알림]관리자 모드로 접속합니다.");
		
		while(true) {
			System.out.println("=".repeat(26) + "Admin");
			System.out.println("1.맛집 추가");
			System.out.println("2.맛집 리스트 조회");
			System.out.println("3.맛집 수정");
			System.out.println("4.맛집 삭제");
			System.out.println("-".repeat(30));
			
			System.out.println("[알림]원하시는 서비스를 눌러주세요.");
			System.out.print(">> ");
			input = sc.nextLine();
			
			if (input.equals("1")) {
				create(sc);
			} else if (input.equals("2")) {
				select();
			} else if (input.equals("3")){
								
			} else if (input.equals("4")){
				
			} else {
				System.out.println("[알림]잘못 누르셨습니다.");
			} 
		}
	}
	
	public void create(Scanner sc) {
		StoreDTO storeDTO = new StoreDTO();
		
		String storeName = sc.nextLine();
		String address = sc.nextLine();
		String tel = sc.nextLine();
		String OpenHour = sc.nextLine();
		String location = sc.nextLine();
		String foodType = sc.nextLine();
		
		adminStoreDAO.create();
	}
	
	public List<StoreDTO> select() {
		adminStoreDAO.select();
		return null;
	}
	
	public void update(StoreDTO storeDTO) {
		adminStoreDAO.update(storeDTO);
	}
	
	public void delete(Long StoreId) {
		adminStoreDAO.delete(StoreId);
	}
}
