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
		
		try {
			storeList = dao.findByLocation(loc, page);
		} catch (Exception e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
		}
		
		return storeList; 
	}
	
	public List<StoreDTO> serviceType(String type, int page) throws FindException {
		List<StoreDTO> storeList = null;
		
		try {
			storeList = dao.findByType(type, page);
		} catch (Exception e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
		}
		
		return storeList;
	}
	
	public List<StoreDTO> serviceSearch(String name, int page) throws FindException {
		List<StoreDTO> storeList = null;
		
		try {
			storeList = dao.findByName(name, page);
		} catch (Exception e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
		}
		
		return storeList;
	}
	
	public int cntStoreLoc(String loc) throws FindException {
		int cnt = 0;
		
		try {
			cnt = dao.countStoreLoc(loc);
		} catch (Exception e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
		}
		
		return cnt; 
	}
	
	public int cntStoreType(String type) throws FindException {
		int cnt = 0;
		
		try {
			cnt = dao.countStoreType(type);
		} catch (Exception e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
		}
		
		return cnt;
	}
	
	public int cntStoreSearch(String name) throws FindException {
		int cnt = 0;
		
		try {
			cnt = dao.countStoreSearch(name);
		} catch (Exception e) {
			throw new FindException("맛집리스트를 조회할 수 없습니다.");
		}
		
		return cnt;
	}
}
