package com.kosa.mango3.customer.dao;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;

public interface CustomerDAO {
	/**
	 * 회원가입한
	 * @param id 아이디 
	 * @param pw 비번 
	 * @throws AddException 아이디가 중복되거나 DB연결에 실패한 경우 발생
	 */
	void insert(String id, String pw) throws AddException;
	
	/**
	 * 아이디에 해당하는 회원 검색
	 * @param id 아이디 
	 * @return 회원객체 
	 * @throws FindException 아이디가 없거나 DB연결에 실패한 경우 발생
	 */
	CustomerDTO selectById(String id) throws FindException;
}
