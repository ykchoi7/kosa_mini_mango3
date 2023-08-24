package com.kosa.mango3.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.db.MySql;
import com.kosa.mango3.store.StoreDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewDAO implements ReviewInterface {
//	private Oracle db;
	private MySql db;
	
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
			conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPwd());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstmt = null;		
		String deleteMyReview = "DELETE review WHERE review_id = ?";
		
		try {
			pstmt = conn.prepareStatement(deleteMyReview);
			pstmt.setLong(1, reviewId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public List<ReviewDTO> selectByCustomer(String loginId) {
		
		List<ReviewDTO> reviewList = new ArrayList<>();
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPwd());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectMyReview = "SELECT r.review_id, s.store_name, r.grade, r.rw_content, TO_CHAR(r.regdate) regdate"
							  + "FROM review r JOIN store s ON r.store_id = s.store_id"
							  + "WHERE login_id = ?";
		
		String mySqlTest = "SELECT r.review_id, s.store_name, r.grade, r.rw_content, created_at\r\n"
						 + "FROM review r JOIN store s ON r.store_id = s.store_id\r\n"
						 + "WHERE r.login_id = ?";
		
		try {
			pstmt = conn.prepareStatement(mySqlTest);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
	
				StoreDTO s = StoreDTO.builder()			
							.storeName(rs.getString("store_name"))
							.build();
				
				ReviewDTO r = ReviewDTO.builder()
						.reviewId(rs.getLong("review_id"))
						.grade(rs.getInt("grade"))
						.comment(rs.getString("rw_content"))
						.regdate(rs.getString("created_at"))
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
