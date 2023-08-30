package com.kosa.mango3.review.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.RemoveException;
import com.kosa.mango3.review.dto.ReviewDTO;
import com.kosa.mango3.store.dto.StoreDTO;

public class ReviewDAOOracle implements ReviewDAO {
	
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mango3";
	private final String PASSWD = "mango3";
	private Connection conn = null;
	
	@Override
	public void create(ReviewDTO reviewDTO) throws AddException {
		
		String insertSQL = "INSERT INTO \"MANGO3\".\"REVIEW\" (REVIEW_ID, GRADE, RW_CONTENT, STORE_ID, LOGIN_ID) "
				+ "VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
					
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
		 	pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, reviewDTO.getGrade());
			pstmt.setString(2, reviewDTO.getComment());
			pstmt.setLong(3, reviewDTO.getStoreDTO().getStoreId());
			pstmt.setString(4, reviewDTO.getCustomerDTO().getLoginId());

			pstmt.executeUpdate();	
		} catch (SQLException e) {
			throw new AddException("( つ｡>﹏<｡)つ 리뷰 등록에 실패하였습니다.");
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public List<ReviewDTO> selectByStoreNo(long storeId, int page) throws FindException {
		List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		int pageSize=5;
		
		String selectSQL = "SELECT rn, review_id, grade, rw_content, TO_CHAR(regdate, 'yy/mm/dd') regdate, login_id\r\n"
				+ "FROM (SELECT ROWNUM rn, a.* \r\n"
				+ "FROM (SELECT rownum, grade, rw_content, login_id, regdate, review_id\r\n"
				+ "FROM review\r\n"
				+ "WHERE store_id = ?\r\n"
				+ "ORDER BY regdate DESC) a )\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setLong(1, storeId);
			pstmt.setInt(2, pageSize*(page-1)+1);
			pstmt.setInt(3, pageSize*page);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				Integer grade = rs.getInt("grade");
				String comment = rs.getString("rw_content");
				String loginId = rs.getString("login_id");
				String regdate = rs.getString("regdate");
				
				
				CustomerDTO customerDTO = CustomerDTO.builder().loginId(loginId).build();			
				ReviewDTO rwDTO = ReviewDTO.builder()
									.grade(grade)
									.comment(comment)
									.regdate(regdate)
									.customerDTO(customerDTO)
									.build();					
			 
				reviewList.add(rwDTO);
				}
			
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 리뷰 조회에 실패하였습니다.");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			} 
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return reviewList;
	}
		
	@Override
	public List<ReviewDTO> selectByGrade(long storeId, int grade, int page) throws FindException {
		List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		int pageSize=5;
		
		String selectSQL = "SELECT rn, review_id, grade, rw_content,  TO_CHAR(regdate, 'yy/mm/dd') regdate, login_id\r\n"
				+ "FROM (SELECT ROWNUM rn, a.* \r\n"
				+ "      FROM (SELECT rownum, grade, rw_content, login_id, regdate, review_id\r\n"
				+ "            FROM review\r\n"
				+ "            WHERE store_id = ? and grade=?\r\n"
				+ "            ORDER BY regdate DESC) a\r\n"
				+ "     )\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setLong(1, storeId);
			pstmt.setInt(2, grade);
			pstmt.setInt(3, pageSize*(page-1)+1);
			pstmt.setInt(4, pageSize*page);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				Integer grades = rs.getInt("grade");
				String comment = rs.getString("rw_content");
				String loginId = rs.getString("login_id");
				String regdate = rs.getString("regdate");		
				
				CustomerDTO customerDTO = CustomerDTO.builder().loginId(loginId).build();			
				ReviewDTO rwDTO = ReviewDTO.builder()
									.grade(grades)
									.comment(comment)
									.regdate(regdate)
									.customerDTO(customerDTO)
									.build();			
			 
				reviewList.add(rwDTO);
				}
			
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 리뷰 조회에 실패하였습니다.");
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			} 
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return reviewList;
	}

	@Override
	public List<ReviewDTO> selectByCustomer(String loginId, int page) throws FindException {
		List<ReviewDTO> reviewList = new ArrayList<>();
		int pageSize = 5;

		String oraclePaging = "SELECT rn, review_id, store_name, grade, rw_content, regdate\r\n"
				+ "FROM (SELECT ROWNUM rn, a.*\r\n"
				+ "      FROM (SELECT r.review_id, s.store_name, r.grade, r.rw_content, TO_CHAR(r.regdate) regdate\r\n"
				+ "            FROM review r JOIN store s ON r.store_id = s.store_id\r\n"
				+ "            WHERE login_id = ?"
				+ "			   ORDER BY regdate desc) a\r\n"
				+ "     )\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(oraclePaging);
			pstmt.setString(1, loginId);
			pstmt.setInt(2, pageSize*(page-1)+1);
			pstmt.setInt(3, pageSize*page);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				StoreDTO s = StoreDTO.builder()			
						.storeName(rs.getString("store_name"))
						.build();

				ReviewDTO r = ReviewDTO.builder()
						.reviewId(rs.getLong("review_id"))
						.grade(rs.getInt("grade"))
						.comment(rs.getString("rw_content"))
						.regdate(rs.getString("regdate"))
						.storeDTO(s)
						.build();

				reviewList.add(r);
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 내 리뷰 조회에 실패하였습니다.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

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

		return reviewList;
	}
	
	@Override
	public void delete(long reviewId) throws RemoveException {
		
		String deleteMyReview = "DELETE review WHERE review_id = ?";
		PreparedStatement pstmt = null;		
				
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(deleteMyReview);
			pstmt.setLong(1, reviewId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RemoveException("( つ｡>﹏<｡)つ 리뷰 삭제에 실패하였습니다.");
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
	public int countStoreReview(long id) throws FindException {

		String selectSQL = "SELECT COUNT(*) FROM review WHERE store_id=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 리뷰를 조회할 수 없습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}			
		}	
	}
	
	@Override
	public int countGradeReview(long id, int grade) throws FindException {

		String selectSQL = "SELECT COUNT(*) FROM review WHERE store_id=? AND grade=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setLong(1, id);
			pstmt.setInt(2, grade);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 리뷰를 조회할 수 없습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}			
		}	
	}
	
	@Override
	public int countMyReview(String loginId) throws FindException {

		String selectSQL = "SELECT COUNT(*) FROM review WHERE login_id=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 리뷰를 조회할 수 없습니다.");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}			
		}	
	}
	
}
