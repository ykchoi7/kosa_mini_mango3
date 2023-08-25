package com.kosa.mango3.store;

import java.util.List;

public interface StoreInterface {

	/**
	 * 지역별 맛집 조회
	 */
	List<StoreDTO> findByLocation(String locName);
	
	/**
	 * 메뉴별 맛집 조회
	 */
	List<StoreDTO> findByType(String typeName);
	
	/**
	 * 맛집 검색
	 */
	List<StoreDTO> findByName(String storeName);
		
	/**
	 * 맛집 상세 조회
	 */
	void storeInfo();
}
