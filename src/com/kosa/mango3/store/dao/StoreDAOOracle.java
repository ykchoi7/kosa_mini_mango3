package com.kosa.mango3.store.dao;

import java.lang.module.FindException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.config.Mango3Config;
import com.kosa.mango3.db.Oracle;
import com.kosa.mango3.review.dao.ReviewDAO;
import com.kosa.mango3.review.dao.ReviewDAOOracle;
import com.kosa.mango3.store.dto.StoreDTO;

public class StoreDAOOracle implements StoreDAO {
	private ReviewDAOOracle rd;
	private Mango3Config mc;
	Oracle oc = new Oracle();
	Connection conn = null;
	
	public StoreDAOOracle(ReviewDAOOracle rd) {
		this.rd = (ReviewDAOOracle) rd;
	}
	
	public StoreDAOOracle() {
	}
	
	@Override
	public List<StoreDTO> findByName(String storeName) throws FindException {
		List<StoreDTO> li = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = oc.DBConnect();
			String selectSQL = "SELECT s.store_id, s.store_name, NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt, s.location, s.food_type \r\n"
					+ "FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
					+ "WHERE INSTR(store_name, ?) > 0\r\n"
					+ "GROUP BY s.store_id, s.store_name, s.location, s.food_type";

			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, storeName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));
				dto.setGrade(rs.getLong("grade"));
				dto.setReviewId(rs.getLong(4));
				dto.setLocation(rs.getString("location"));
				dto.setFoodType(rs.getString("food_type"));
				li.add(dto);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new FindException("가게 조회 실패");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null)  {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
		return li;
	}

	@Override
	public List<StoreDTO> findByLocation(String locName) throws FindException{
		List<StoreDTO> li = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = oc.DBConnect();
			String selectSQL = "SELECT store.store_id, store.store_name, NVL(AVG(review.grade), 0) as grade, NVL(COUNT(review.review_id),0) as reviewcnt, store.location, store.food_type \r\n"
					+ "FROM store FULL OUTER JOIN review ON store.store_id = review.store_id\r\n"
					+ "WHERE store.location = ?\r\n"
					+ "GROUP BY store.store_id, store.store_name, store.location, store.food_type";
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, locName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));
				dto.setGrade(rs.getLong("grade"));
				dto.setReviewId(rs.getLong(4));
				dto.setLocation(rs.getString("location"));
				dto.setFoodType(rs.getString("food_type"));
				li.add(dto);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new FindException("가게 조회 실패");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null)  {
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
		return li;
	}

	@Override
	public List<StoreDTO> findByType(String typeName) throws FindException {
		List<StoreDTO> li = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = oc.DBConnect();
			String selectSQL = "SELECT s.store_id, s.store_name, NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt, s.location, s.food_type \r\n"
					+ "FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
					+ "WHERE s.food_type = ?\r\n"
					+ "GROUP BY s.store_id, s.store_name, s.location, s.food_type";
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, typeName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));
				dto.setGrade(rs.getLong("grade"));
				dto.setReviewId(rs.getLong("reviewcnt"));
				dto.setLocation(rs.getString("location"));
				dto.setFoodType(rs.getString("food_type"));
				li.add(dto);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new FindException("가게 조회 실패");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null)  {
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
		return li;
	}

	@Override
	public List<StoreDTO> storeInfo(long storeId) throws FindException {
		List<StoreDTO> li = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = oc.DBConnect();
		
		String selectSQL = "SELECT s.*, NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt\r\n"
				+ "FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
				+ "WHERE s.store_id = ?\r\n"
				+ "GROUP BY s.store_id, s.store_name, s.location, s.food_type, s.address, s.tel, s.store_hour";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setLong(1, storeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				long store_id = rs.getLong("store_id");
				String store_name = rs.getString("store_name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String store_hour = rs.getString("store_hour");
				String location = rs.getString("location");
				String food_type = rs.getString("food_type");
				Long grade = rs.getLong("grade");
				Long reviewcnt = rs.getLong("reviewcnt");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new FindException("가게 조회 실패");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null)  {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
//		rd.create();
		return li;
	}

}
