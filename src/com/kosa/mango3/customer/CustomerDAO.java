package com.kosa.mango3.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDAO implements CustomerInterface {

	ArrayList<CustomerDTO> customerInfo=new ArrayList<CustomerDTO>();
	Scanner sc=new Scanner(System.in);
	Connection conn;
	
	public CustomerDAO() {
		conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		listDB();
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) { }
		}
	}

	@Override
	public void join() {	// TODO 패스워드 암호화
		//1. customer 객체 생성
		CustomerDTO newCustomer=new CustomerDTO();

		//2. db 연결
		conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3. 회원가입 수행
		joinService(newCustomer);

		//4. db 종료
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) { }
		}
	}

	private void joinService(CustomerDTO newCustomer) {
		while(true) {
			//2. id 입력받기
			System.out.print("사용할 ID 입력 : ");
			String id=sc.nextLine();

			//3. id 중복 체크
			if(checkID(id)==-1) {
				newCustomer.setLoginId(id);
				break;
			} else System.out.println("이미 존재하는 ID입니다.");
		}

		while(true) {
			//4. pw 입력받기
			System.out.print("사용할 PW 입력 : ");
			String pw=sc.nextLine();

			//5. pw 확인
			System.out.print("비밀번호 확인 : ");
			String checkPw=sc.nextLine();
			if(pw.equals(checkPw)) {
				newCustomer.setPwd(pw);
				customerInfo.add(newCustomer);

				//6. db
				if(joinDB(newCustomer.getLoginId(), pw)) {
					System.out.println("회원가입이 완료되었습니다.");
					return;
				} else System.out.println("에러");

			} else System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}

	private void listDB() {
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			String selectSQL="SELECT * FROM customer";
			rs=stmt.executeQuery(selectSQL); //송신
			while(rs.next()) {
				customerInfo.add(new CustomerDTO(rs.getString("login_id"), rs.getString("pwd")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) { }
			}
			
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) { }
			}
		}
	}

	private boolean joinDB(String id, String pw) {
		PreparedStatement pstmt=null;
		String insertSQL="INSERT INTO customer (login_id, pwd)\r\n"
				+ "VALUES (?,?)";

		try {
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			int rowcnt=pstmt.executeUpdate();
			System.out.println(rowcnt+"건 추가 성공");
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int checkID(String id) {
		for(int i=0;i<customerInfo.size();i++) {
			//1. id 존재 O면 해당 인덱스 리턴
			if(customerInfo.get(i).getLoginId().equals(id)) return i;
		}

		//2. id 존재 X면 -1 리턴
		return -1;
	}

	@Override
	public String login() {	// TODO 패스워드 입력 안보이게(마스킹)
		while(true) {
			//2. 로그인 서비스 함수
			String id=loginService();

			//3. -1 리턴 아닐 시 로그인 성공
			if(!id.equals("-1")) {
				System.out.println("로그인에 성공하였습니다.");
				return id;
			}
			else {
				//3. 1 입력 시 재로그인, 아닐 시 로그인 취소
				System.out.println("로그인에 실패하였습니다.");
				System.out.print("재로그인을 원하시면 1을 입력해주세요 : ");
				String relogin=sc.nextLine();
				if(!relogin.equals("1")) break;
			}
		}

		return "-1";
	}

	private String loginService() {
		//1. 로그인 정보 입력받기
		System.out.print("ID : ");
		String id=sc.nextLine();
		System.out.print("PW : ");
		String pw=sc.nextLine();

		int check=checkID(id);

		//2. id 존재 여부 확인
		if(check==-1) return "-1";

		//3. pw 일치 여부 확인
		if(customerInfo.get(check).getPwd().equals(pw)) return id;
		else return "-1";
	}

}
