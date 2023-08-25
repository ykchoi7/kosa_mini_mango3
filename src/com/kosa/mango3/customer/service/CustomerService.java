package com.kosa.mango3.customer.service;

import com.kosa.mango3.customer.dao.CustomerDAO;
import com.kosa.mango3.customer.dao.CustomerDAOOracle;
import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;

public class CustomerService {

	private CustomerDAO dao; 

	public CustomerService() {
		dao = new CustomerDAOOracle();
	}

	public void join(String loginId, String pwd) throws AddException {
		try {
			dao.insert(loginId, pwd);
		} catch (AddException e) {
			//e.printStackTrace();
			throw new AddException("ID가 중복됩니다.");
		}
	}

	public CustomerDTO login(String loginId, String pwd) throws FindException {
		try {
			CustomerDTO c=dao.selectById(loginId);
			if( !c.getPwd().equals(pwd) ) {
				throw new FindException();
			}
			return c;
		} catch (FindException e) {
			//e.printStackTrace();
			throw new FindException("ID가 존재하지 않거나 비밀번호가 올바르지 않습니다.");
		}
	}

}
