package com.kosa.mango3.customer.service;

import com.kosa.mango3.customer.dao.CustomerDAO;
import com.kosa.mango3.customer.dao.CustomerDAOOracle;
import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.ModifyException;
import com.kosa.mango3.exception.RemoveException;

public class CustomerService {

	private CustomerDAO dao; 

	public CustomerService() {
		this.dao = new CustomerDAOOracle();
	}

	public void join(String loginId, String pwd) throws AddException {
		try {
			dao.insert(loginId, pwd);
		} catch (AddException e) {
			//e.printStackTrace();
			throw new AddException("( つ｡>﹏<｡)つ ID가 중복됩니다.");
		}
	}
	
	public CustomerDTO login(String loginId, String pwd) throws FindException {
		try {
			CustomerDTO c=dao.selectById(loginId);
			if(!c.getPwd().equals(pwd)) throw new FindException();
			if(c.getStatus()==0) throw new FindException();
			return c;
		} catch (FindException e) {
			//e.printStackTrace();
			throw new FindException("( つ｡>﹏<｡)つ ID가 존재하지 않거나 PW가 올바르지 않습니다.");
		}
	}
	
	public void changePwd(String id, String newPwd) {
		try {
			dao.updatePwd(id, newPwd);
			System.out.println("༼ つ ◕_◕ ༽つ PW 변경이 완료되었습니다.");
		} catch (ModifyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removeCustomer(String id) {
		try {
			dao.updateStatus(id);
			System.out.println("༼ つ ◕_◕ ༽つ 회원 탈퇴가 완료되었습니다.");
		} catch (RemoveException e) {
			System.out.println(e.getMessage());
		}
	}

}
