package com.kosa.mango3.customer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;

public class CustomerDAOOracle implements CustomerDAO {

	@Override
	public void insert(String id, String pw) throws AddException{

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
			String insertSQL="INSERT INTO customer (login_id, pwd)\r\n"
					+ "VALUES (?,?)";
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new AddException("고객 추가 실패");
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
	public CustomerDTO selectById(String id) throws FindException {
		
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango3";
		String password = "mango3";

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 연결 성공");
			String selectSQL = "SELECT * FROM customer WHERE login_id=?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new CustomerDTO(rs.getString("login_id"), rs.getString("pwd"));
			}else {
				throw new FindException("고객이 없습니다");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new FindException("고객 조회 실패");
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
