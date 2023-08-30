package com.kosa.mango3.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.ModifyException;
import com.kosa.mango3.exception.RemoveException;
import com.kosa.mango3.store.dto.StoreDTO;

public class StoreAdmin {
	Scanner sc=new Scanner(System.in);

	// 관리자 페이지 메인
	public void adminPage(String num) throws FindException {
		if(num.equals("0")) {
			try {
				allStore();
			} catch (FindException e) {
				throw new FindException("음식점 조회에 실패하였습니다.");
			}
		} else if(num.equals("1")) {

			System.out.print("음식점명 : ");
			String name=sc.nextLine();
			System.out.print("음식점 주소 : ");
			String address=sc.nextLine();
			System.out.print("음식점 연락처 : ");
			String tel=sc.nextLine();
			System.out.print("음식점 영업시간 : ");
			String hour=sc.nextLine();
			System.out.print("음식점 위치 : ");
			String loc=sc.nextLine();
			System.out.print("음식점 종류 : ");
			String type=sc.nextLine();

			System.out.println("음식점을 추가하시겠습니까?");
			System.out.println("계속 하시려면 아무 키나 눌러주세요. (0. 취소)");
			System.out.print("✔️ ");
			String fin=sc.nextLine();
			if(fin.equals("0")) {
				System.out.println("실행이 취소되었습니다.");
				return;
			}

			StoreDTO newSDTO;
			try {
				newSDTO = StoreDTO.builder()
						.storeId((long)(countStore()+1))
						.storeName(name)
						.address(address)
						.tel(tel)
						.storeHour(hour)
						.location(loc)
						.foodType(type)
						.build();

				createStore(newSDTO);
				System.out.println("음식점이 추가되었습니다.");
			} catch (FindException e) {
				System.out.println(e.getMessage());
			} catch (AddException e) {
				System.out.println(e.getMessage());
			}

		} else if(num.equals("2")) {

			try {
				allStore();
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}

			System.out.print("수정할 음식점의 인덱스 : ");
			String row=sc.nextLine();

			System.out.println("1. 음식점명");
			System.out.println("2. 주소");
			System.out.println("3. 연락처");
			System.out.println("4. 영업시간");
			System.out.println("5. 위치");
			System.out.println("6. 음식 종류");
			System.out.print("수정할 항목 : ");
			String update=sc.nextLine();

			String[] cal= {"store_name", "address", "tel", "store_hour", "location", "food_type"};
			System.out.print("수정된 내용 : ");
			String content=sc.nextLine();

			System.out.println("음식점을 수정하시겠습니까?");
			System.out.println("계속 하시려면 아무 키나 눌러주세요. (0. 취소)");
			System.out.print("✔️ ");
			String fin=sc.nextLine();
			if(fin.equals("0")) {
				System.out.println("실행이 취소되었습니다.");
				return;
			}

			try {
				replaceStore(row, cal[Integer.parseInt(update)-1], content);
				System.out.println("음식점이 수정되었습니다.");
			} catch (ModifyException e) {
				System.out.println(e.getMessage());
			}

		} else if(num.equals("3")) {

			try {
				allStore();
			} catch (FindException e) {
				System.out.println(e.getMessage());
			}

			System.out.print("삭제할 음식점의 인덱스 : ");
			String row=sc.nextLine();

			System.out.println("음식점을 삭제하시겠습니까?");
			System.out.println("계속 하시려면 아무 키나 눌러주세요. (0. 취소)");
			System.out.print("✔️ ");
			String fin=sc.nextLine();
			if(fin.equals("0")) {
				System.out.println("실행이 취소되었습니다.");
				return;
			}

			try {
				deleteStore(row);
				System.out.println("음식점이 삭제되었습니다.");
			} catch (RemoveException e) {
				System.out.println(e.getMessage());
			}

		} else System.out.println("다시 입력해주세요.");
	}

	// 음식점 추가
	private void createStore(StoreDTO storeDTO) throws AddException {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String insertSQL="INSERT INTO store (store_id, store_name, address, tel, store_hour, location, food_type)\r\n"
					+ "VALUES (?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setLong(1, storeDTO.getStoreId());
			pstmt.setString(2, storeDTO.getStoreName());
			pstmt.setString(3, storeDTO.getAddress());
			pstmt.setString(4, storeDTO.getTel());
			pstmt.setString(5, storeDTO.getStoreHour());
			pstmt.setString(6, storeDTO.getLocation());
			pstmt.setString(7, storeDTO.getFoodType());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AddException("음식점 추가에 실패하였습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}

	}

	// 음식점 수정
	private void replaceStore(String index, String update, String content) throws ModifyException {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String updateSQL = "UPDATE store SET "+update+"='"+content+"' WHERE store_id="+Long.parseLong(index);
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.executeQuery();
		} catch (SQLException e) {
			throw new ModifyException("음식점 수정에 실패하였습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}	

	}

	// 음식점 삭제
	private void deleteStore(String index) throws RemoveException {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String deleteSQL = "DELETE FROM store WHERE store_id="+Long.parseLong(index);
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.executeQuery();
		} catch (SQLException e) {
			throw new RemoveException("음식점 삭제에 실패하였습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}	

	}

	// 음식점 총 개수 조회
	private int countStore() throws FindException {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String selectSQL = "SELECT COUNT(*) FROM store";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (SQLException e) {
			throw new FindException("음식점 개수 조회에 실패하였습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}	

	}

	// 전체 음식점 목록 출력
	private void allStore() throws FindException {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String selectSQL = "SELECT * FROM store";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				System.out.print(rs.getString("store_id")+". ");
				System.out.print("음식점명 : "+rs.getString("store_name")+" | ");
				System.out.print("주소 : "+rs.getString("address")+" | ");
				System.out.print("연락처 : "+rs.getString("tel")+" | ");
				System.out.print("영업시간 : "+rs.getString("store_hour")+" | ");
				System.out.print("위치 : "+rs.getString("location")+" | ");
				System.out.println("음식 종류 : "+rs.getString("food_type")+" | ");
			}
		} catch (SQLException e) {
			throw new FindException("음식점 조회에 실패하였습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}

	}

}
