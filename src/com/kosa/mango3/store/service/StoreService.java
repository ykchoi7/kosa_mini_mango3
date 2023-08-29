package com.kosa.mango3.store.service;

import java.util.List;

import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.store.dao.StoreDAO;
import com.kosa.mango3.store.dao.StoreDAOOracle;
import com.kosa.mango3.store.dto.StoreDTO;

public class StoreService {
	private StoreDAO dao;
	
	public StoreService() {
		this.dao = new StoreDAOOracle();
	}
	
	public List<StoreDTO> serviceLoc(String loc, int page) throws FindException {
		List<StoreDTO> storeList = null;
		storeList = dao.findByLocation(loc, page);
		
		return storeList; 
	}
	
	public List<StoreDTO> serviceType(String type, int page) throws FindException {
		List<StoreDTO> storeList = null;
		storeList = dao.findByType(type, page);
		
		return storeList;
	}
	
	public List<StoreDTO> serviceSearch(String name, int page) throws FindException {
		List<StoreDTO> storeList = null;
		storeList = dao.findByName(name, page);
		
		return storeList;
	}
	
	public int cntStoreLoc(String loc) throws FindException {
		int cnt = 0;
		cnt = dao.countStoreLoc(loc);
		
		return cnt; 
	}
	
	public int cntStoreType(String type) throws FindException {
		int cnt = 0;
		cnt = dao.countStoreType(type);
		
		return cnt;
	}
	
	public int cntStoreSearch(String name) throws FindException {
		int cnt = 0;
		cnt = dao.countStoreSearch(name);
		
		return cnt;
	}
}
