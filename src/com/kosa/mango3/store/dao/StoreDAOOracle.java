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

	private Connection conn = null;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mango3";
	private final String PASSWD = "mango3";

	@Override
	public List<StoreDTO> findByLocation(String locName, int page) throws FindException{
		List<StoreDTO> storeList = new ArrayList<>();
		int pageSize = 5;



		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			String selectSQL = "SELECT * FROM (SELECT ROWNUM rn, a.*\r\n"
					+ "FROM (SELECT s.store_id, s.store_name, s.location, s.food_type\r\n"
					+ ", NVL(s.address, '-') as address, NVL(s.tel, '-') as tel, NVL(s.store_hour,'-') as store_hour\r\n"
					+ ", NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt\r\n"
					+ "FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
					+ "WHERE s.location = ?\r\n"
					+ "GROUP BY s.store_id, s.store_name, s.location, s.food_type, s.address, s.tel, s.store_hour "
					+ "ORDER BY AVG(r.grade)) a)\r\n"
					+ "WHERE rn BETWEEN ? AND ?";
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
				dto.setAddress(rs.getString("address"));
				dto.setStoreHour(rs.getString("store_hour"));
				dto.setTel(rs.getString("tel"));
				storeList.add(dto);
			}
		} catch (SQLException e) {
			//			e.printStackTrace();
			throw new FindException("( つ｡>﹏<｡)つ 음식점을 조회할 수 없습니다.");
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



		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			String selectSQL = "SELECT * FROM (SELECT ROWNUM rn, a.*\r\n"
					+ "FROM (SELECT s.store_id, s.store_name, s.location, s.food_type\r\n"
					+ ", NVL(s.address, '-') as address, NVL(s.tel, '-') as tel, NVL(s.store_hour,'-') as store_hour\r\n"
					+ ", NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt\r\n"
					+ "FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
					+ "WHERE s.food_type = ?\r\n"
					+ "GROUP BY s.store_id, s.store_name, s.location, s.food_type, s.address, s.tel, s.store_hour "
					+ "ORDER BY AVG(r.grade)) a)\r\n"
					+ "WHERE rn BETWEEN ? AND ?";
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
				dto.setAddress(rs.getString("address"));
				dto.setStoreHour(rs.getString("store_hour"));
				dto.setTel(rs.getString("tel"));
				storeList.add(dto);
			}

		} catch (SQLException e) {
			//			System.out.println(e.getMessage());
			throw new FindException("( つ｡>﹏<｡)つ 음식점을 조회할 수 없습니다.");
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



		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			String selectSQL = "SELECT * FROM (SELECT ROWNUM rn, a.*\r\n"
					+ "FROM (SELECT s.store_id, s.store_name, s.location, s.food_type\r\n"
					+ ", NVL(s.address, '-') as address, NVL(s.tel, '-') as tel, NVL(s.store_hour,'-') as store_hour\r\n"
					+ ", NVL(AVG(r.grade), 0) as grade, NVL(COUNT(r.review_id),0) as reviewcnt\r\n"
					+ "FROM store s FULL OUTER JOIN review r ON s.store_id = r.store_id\r\n"
					+ "WHERE INSTR(s.store_name, ?) > 0\r\n"
					+ "GROUP BY s.store_id, s.store_name, s.location, s.food_type, s.address, s.tel, s.store_hour "
					+ "ORDER BY AVG(r.grade)) a)\r\n"
					+ "WHERE rn BETWEEN ? AND ?";
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
				dto.setAddress(rs.getString("address"));
				dto.setStoreHour(rs.getString("store_hour"));
				dto.setTel(rs.getString("tel"));
				storeList.add(dto);
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 음식점을 조회할 수 없습니다.");
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

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String selectSQL = "SELECT COUNT(*) FROM store WHERE location=?";

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);

			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, loc);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 음식점을 조회할 수 없습니다.");
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

		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			String selectSQL = "SELECT COUNT(*) FROM store WHERE food_type=?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (Exception e) {
			throw new FindException("( つ｡>﹏<｡)つ 음식점을 조회할 수 없습니다.");
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


		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			String selectSQL = "SELECT COUNT(*) FROM store WHERE INSTR(store_name, ?) > 0";

			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getInt("COUNT(*)");
			}else {
				throw new FindException("");
			}
		} catch (SQLException e) {
			throw new FindException("( つ｡>﹏<｡)つ 음식점을 조회할 수 없습니다.");
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
