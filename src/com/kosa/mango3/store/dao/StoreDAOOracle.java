package com.kosa.mango3.store.dao;

import java.lang.module.FindException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.store.dto.StoreDTO;

public class StoreDAOOracle implements StoreDAO {
	
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mango3";
	private final String PASSWD = "mango3";
	private Connection conn = null;
		
	@Override
	public List<StoreDTO> findByLocation(String locName, int page) throws FindException{
		List<StoreDTO> storeList = new ArrayList<>();
		int pageSize = 5;
		
		String selectSQL = "SELECT *\r\n"
				+ "FROM (SELECT ROWNUM rn, a.*\r\n"
				+ "      FROM (SELECT store.store_id, store.store_name, NVL(AVG(review.grade), 0) as grade, NVL(COUNT(review.review_id),0) as reviewcnt, store.location, store.food_type\r\n"
				+ "            FROM store FULL OUTER JOIN review ON store.store_id = review.store_id\r\n"
				+ "            WHERE store.location = ?\r\n"
				+ "            GROUP BY store.store_id, store.store_name, store.location, store.food_type) a\r\n"
				+ "     )\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, locName);
			pstmt.setInt(2, pageSize*(page-1)+1);
			pstmt.setInt(3, pageSize*page);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));
				dto.setGrade(rs.getLong("grade"));
				dto.setReviewCnt(rs.getLong("reviewcnt"));
				dto.setLocation(rs.getString("location"));
				dto.setFoodType(rs.getString("food_type"));
				storeList.add(dto);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
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
		return storeList;
	}

	@Override
	public List<StoreDTO> findByType(String typeName, int page) throws FindException {
		List<StoreDTO> storeList = new ArrayList<>();
		int pageSize = 5;
		
		String selectSQL = "SELECT *\r\n"
				+ "FROM (SELECT ROWNUM rn, a.*\r\n"
				+ "      FROM (SELECT s.store_id, s.store_name, NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt, s.location, s.food_type\r\n"
				+ "            FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
				+ "            WHERE s.food_type = ?\r\n"
				+ "            GROUP BY s.store_id, s.store_name, s.location, s.food_type) a\r\n"
				+ "      )\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);

			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, typeName);
			pstmt.setInt(2, pageSize*(page-1)+1);
			pstmt.setInt(3, pageSize*page);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));				
				dto.setGrade(rs.getLong("grade"));
				dto.setReviewCnt(rs.getLong("reviewcnt"));
				dto.setLocation(rs.getString("location"));
				dto.setFoodType(rs.getString("food_type"));
				storeList.add(dto);
			}
		} catch (SQLException e) {
//			System.out.println(e.getMessage());
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
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
		return storeList;
	}

	@Override
	public List<StoreDTO> findByName(String storeName, int page) throws FindException {
		List<StoreDTO> storeList = new ArrayList<>();
		int pageSize = 5;
		
		String selectSQL = "SELECT *\r\n"
				+ "FROM (SELECT ROWNUM rn, a.*\r\n"
				+ "      FROM (SELECT s.store_id, s.store_name, NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt, s.location, s.food_type\r\n"
				+ "            FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
				+ "            WHERE INSTR(store_name, ?) > 0\r\n"
				+ "            GROUP BY s.store_id, s.store_name, s.location, s.food_type) a\r\n"
				+ "      )\r\n"
				+ "WHERE rn BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, storeName);
			pstmt.setInt(2, pageSize*(page-1)+1);
			pstmt.setInt(3, pageSize*page);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));
				dto.setGrade(rs.getLong("grade"));
				dto.setReviewCnt(rs.getLong("reviewcnt"));
				dto.setLocation(rs.getString("location"));
				dto.setFoodType(rs.getString("food_type"));
				storeList.add(dto);
			}
		} catch (SQLException e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
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
		return storeList;
	}
	
	@Override
	public int countStoreLoc(String loc) throws FindException {

		String selectSQL = "SELECT COUNT(*) FROM store WHERE location=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, loc);
			rs = pstmt.executeQuery();
			
			return rs.getInt("COUNT(*)");
		} catch (Exception e) {
			throw new FindException("맛집리스트 전체 갯수를 조회할 수 없습니다.");
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
	
	@Override
	public int countStoreType(String type) throws FindException {

		String selectSQL = "SELECT COUNT(*) FROM store WHERE food_type=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			
			return rs.getInt("COUNT(*)");
		} catch (Exception e) {
			throw new FindException("맛집리스트 전체 갯수를 조회할 수 없습니다.");
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
	
	@Override
	public int countStoreSearch(String name) throws FindException {

		String selectSQL = "SELECT COUNT(*) FROM store WHERE INSTR(store_name, ?) > 0";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			return rs.getInt("COUNT(*)");
		} catch (SQLException e) {
			throw new FindException("맛집리스트 전체 갯수를 조회할 수 없습니다.");
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
