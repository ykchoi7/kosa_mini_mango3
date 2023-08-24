package com.kosa.mango3.store;

import java.util.List;

public interface StoreInterface {
	/**
	 * 지역별 맛집 조회
	 */
	List<StoreDTO> findByLocation();
	
	/**
	 * 메뉴별 맛집 조회
	 */
	List<StoreDTO> findByType();
	
	/**
	 * 맛집 검색
	 */
	List<StoreDTO> findByName(String storeName);
		
	/**
	 * 맛집 상세 조회
	 */
	void storeInfo();
}
