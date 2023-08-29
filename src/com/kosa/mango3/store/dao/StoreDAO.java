package com.kosa.mango3.store.dao;

import java.lang.module.FindException;
import java.util.List;

import com.kosa.mango3.store.dto.StoreDTO;

public interface StoreDAO {

	/**
	 * 지역별 맛집 조회
	 * @param locName 지역명
	 * @return
	 * @throws FindException
	 */
	List<StoreDTO> findByLocation(String locName) throws FindException;
	
	/**
	 * 메뉴별 맛집 조회
	 * @param typeName 음식종류
	 * @return
	 * @throws FindException
	 */
	List<StoreDTO> findByType(String typeName) throws FindException;
	
	/**
	 * 맛집 검색
	 * @param storeName 가게명
	 * @return
	 * @throws FindException
	 */
	List<StoreDTO> findByName(String storeName) throws FindException;
		
	/**
	 * 맛집 상세 조회
	 * @throws FindException
	 */
	List<StoreDTO> storeInfo(long storeId) throws FindException;
}
