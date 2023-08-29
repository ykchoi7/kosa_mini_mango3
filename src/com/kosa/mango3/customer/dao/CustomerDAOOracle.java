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
	
	private Connection conn = null;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "mango3";
	private final String password = "mango3";
	
	@Override
	public void insert(String id, String pw) throws AddException{
		
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String insertSQL="INSERT INTO customer (login_id, pwd, status)\r\n"
					+ "VALUES (?,?,?)";
			pstmt=conn.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setInt(3, 1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new AddException("( つ｡>﹏<｡)つ 회원가입에 실패하였습니다.");
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
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String selectSQL = "SELECT * FROM customer WHERE login_id=?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new CustomerDTO(rs.getString("login_id"), rs.getString("pwd"), rs.getInt("status"));
			}else {
				throw new FindException("( つ｡>﹏<｡)つ ID가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new FindException("( つ｡>﹏<｡)つ ID가 존재하지 않습니다.");
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
	
	public void updatePwd(String id, String newPwd) throws ModifyException {
		
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String updateSQL = "UPDATE customer SET pwd=? WHERE login_id=?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ModifyException("( つ｡>﹏<｡)つ PW 변경에 실패하였습니다.");
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
	
	public void updateStatus(String id) throws RemoveException {

		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println("Oracle DB 연결 성공");
			String updateSQL = "UPDATE customer SET status=0 WHERE login_id=?";
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			throw new RemoveException("( つ｡>﹏<｡)つ 회원 탈퇴를 실패하였습니다.");
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
