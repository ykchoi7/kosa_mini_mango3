package com.kosa.mango3.store.dao;

import java.lang.module.FindException;
import java.util.List;

import com.kosa.mango3.store.dto.StoreDTO;

public interface StoreDAO {

	/**
	 * 지역별 음식점 조회
	 * @param locName
	 * @param page
	 * @return
	 * @throws FindException
	 */
	List<StoreDTO> findByLocation(String locName, int page) throws FindException;
	
	/**
	 * 메뉴별 음식점 조회
	 * @param typeName
	 * @param page
	 * @return
	 * @throws FindException
	 */
	List<StoreDTO> findByType(String typeName, int page) throws FindException;
	
	/**
	 * 음식점명 검색
	 * @param storeName
	 * @param page
	 * @return
	 * @throws FindException
	 */
	List<StoreDTO> findByName(String storeName, int page) throws FindException;

	/**
	 * 지역별 음식점 총 갯수
	 * @param loc
	 * @return
	 * @throws FindException
	 */
	int countStoreLoc(String loc) throws FindException;
	
	/**
	 * 메뉴별 음식점 총 갯수
	 * @param type
	 * @return
	 * @throws FindException
	 */
	int countStoreType(String type) throws FindException;
	
	/**
	 * 검색된 음식점 총 갯수 
	 * @param name
	 * @return
	 * @throws FindException
	 */
	int countStoreSearch(String name) throws FindException;
	
}
