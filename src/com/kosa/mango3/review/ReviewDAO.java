package com.kosa.mango3.review;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.customer.CustomerDTO;
import com.kosa.mango3.db.Oracle;
import com.kosa.mango3.store.StoreDTO;

public class ReviewDAO implements ReviewInterface {
	private List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();	

	Oracle oc = new Oracle();
	Connection conn = null;
		
	@Override
	public List<ReviewDTO> selectByStore(int storeId) {
		conn = oc.DBConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT grade, rw_content, login_id, regdate, store_id" 
				+ " FROM review"
				+ " WHERE store_id =?"
				+ " ORDER BY regdate DESC";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {    			
				int grade = rs.getInt("grade");
				String comment = rs.getString("rw_content");
				String login_id = rs.getString("login_id");
				String store_id = rs.getString("store_id");
				Date regdate = rs.getDate("regdate");
				
			
				CustomerDTO customerDTO = CustomerDTO.builder().loginId(login_id).build();			
				ReviewDTO reviewDTO = ReviewDTO.builder()
									.grade(grade)
									.comment(comment)
									.regdate(regdate)
									.storeDTO(StoreDTO.builder().storeId(store_id).build())
									.customerDTO(customerDTO)
									.build();			
			
				reviewList.add(reviewDTO);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			} 
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return this.reviewList;
	}
	
	@Override
	public List<ReviewDTO> selectByGrade(int storeId, int grade) {
		selectByStore(storeId);
		int cnt = 0;
		for(int i = 0; i<reviewList.size(); i++) {
			ReviewDTO d = reviewList.get(i);	
			if(d.getGrade() == grade) {
				cnt++;
				String score = changeGd(d.getGrade());
				String loginId = d.getCustomerDTO().getLoginId();
				System.out.println("'--------------------------------------------");	
				System.out.println(++i +". " + loginId + " - " + score);
				System.out.println("ㄴ " + d.getComment());
				System.out.println("                               " + d.getRegdate());
			}
		}
		if(cnt == 0) {
			System.out.println("해당하는 리뷰가 없습니다");
		}
		return null;
	}
	
	@Override
	public void create() {
		conn = oc.DBConnect();
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO \"MANGO3\".\"REVIEW\" (REVIEW_ID, GRADE, RW_CONTENT, STORE_ID, LOGIN_ID) "
							+ "VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1,  "id19");
			pstmt.setString(2,  "p19");
			pstmt.setString(3,  "권");
			pstmt.setString(4,  "권");
			int rowcnt = pstmt.executeUpdate();	// 반환값이 int 타입,
			System.out.println(rowcnt + "건 추가 성공");
			//conn.commit(); auto commit 끄는법
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void delete(int reviewId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReviewDTO> selectByCustomer(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String changeGd(int grade) {
		String changeGd = null;
		switch(grade) {
			case 5 :
				changeGd = "맛있다";
				break;
			case 3 :
				changeGd = "괜찮다";
				break;
			case 1 :
				changeGd = "별로";
				break;
		}
		return changeGd;
	}
	
	public void printReviews(int storeId) {
		selectByStore(storeId);		
		for(int i = 0; i<reviewList.size(); i++) {
			ReviewDTO d = reviewList.get(i);
			String score = changeGd(d.getGrade());
			String loginId = d.getCustomerDTO().toString();
			System.out.println("'--------------------------------------------");	
			System.out.println(++i +". " + loginId + " - " + score);
			System.out.println("ㄴ " + d.getComment());
			System.out.println("                               " + d.getRegdate());
		}
	}
}
