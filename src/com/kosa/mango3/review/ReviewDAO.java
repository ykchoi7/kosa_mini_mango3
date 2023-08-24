package com.kosa.mango3.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.customer.CustomerDTO;
import com.kosa.mango3.store.StoreDTO;

public class ReviewDAO implements ReviewInterface {

	private String url = "jdbc:mysql://localhost:3306/mango3?serverTimezone=UTC";
	private String user = "root";
	private String pwd = "root";
	
	@Override
	public List<ReviewDTO> selectByStore(Long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewDTO> selectByGrade(Long storeId, Integer grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long reviewId) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String deleteMyReview = "DELETE Review WHERE review_id = ?";
		
		try {
			pstmt = conn.prepareStatement(deleteMyReview);
			pstmt.setLong(1, reviewId);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<ReviewDTO> selectByCustomer(String loginId) {
		
		List<ReviewDTO> reviewList = new ArrayList<>();
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectMyReview = "SELECT * s.store_name, r.grade, r.rw_content, TO_CHAR(r.regdate), r.review_id"
				+ "FROM store s JOIN review r ON s.store_id = r.store_id"
				+ "WHERE s.store_id IN "
				+ "(SELECT store_id"
				+ "FROM review"
				+ "WHERE login_id = ?";
		
		try {
			pstmt = conn.prepareStatement(selectMyReview);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CustomerDTO c = CustomerDTO.builder()
							.loginId(rs.getString("loginId"))
							.build();
				
				StoreDTO s = StoreDTO.builder()			
							.storeName(rs.getString("storeName"))
							.build();
				
				ReviewDTO r = ReviewDTO.builder()
						.reviewId(rs.getLong("reviewId"))
						.grade(rs.getInt("grade"))
						.comment(rs.getString("comment"))
						.regdate(rs.getString("regdate"))
						.customerDTO(c)
						.storeDTO(s)
						.build();

				reviewList.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return reviewList;
	}

}
