package com.kosa.mango3.customer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.ModifyException;
import com.kosa.mango3.exception.RemoveException;

public class CustomerDAOOracle implements CustomerDAO {
	
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "mango3";
	private final String PASSWD = "mango3";
	private Connection conn = null;
	
	@Override
	public void insert(String id, String pw) throws AddException{
		
		String insertSQL="INSERT INTO customer (login_id, pwd) VALUES (?,?)";
		
		PreparedStatement pstmt=null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AddException("회원 가입 실패");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}			
		}	
		
	}

	@Override
	public CustomerDTO selectById(String id) throws FindException {
		
		String selectSQL = "SELECT * FROM customer WHERE login_id=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new CustomerDTO(rs.getString("login_id"), rs.getString("pwd"));
			}else {
				throw new FindException("고객이 없습니다");
			}
		} catch (SQLException e) {
			throw new FindException("회원 조회 실패");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}			
		}	

	}
	
	@Override
	public void updatePwd(String id, String newPwd) throws ModifyException {
		
		String updateSQL = "UPDATE customer SET pwd=? WHERE login_id=?";
		
		PreparedStatement pstmt=null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			throw new ModifyException("비밀번호 변경 실패");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}			
		}	
		
	}
	
	@Override
	public void delete(String id) throws RemoveException {

		String deleteSQL = "DELETE FROM customer WHERE login_id=?";
		
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			//System.out.println("Oracle DB 연결 성공");
			
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1,  id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			throw new RemoveException("회원 삭제 실패");
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(conn !=null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}			
		}	
		
	}

}
