package com.kosa.mango3.store.admin;

import java.util.List;

import com.kosa.mango3.store.StoreDTO;

public interface AdminStoreInterface {
	/**
	 * 맛집 추가
	 */
	void create();
	
	/**
	 * 맛집 리스트 조회
	 * @return 
	 */
	List<StoreDTO> select();
	
	/**
	 * 맛집 수정
	 * @param storeDTO
	 */
	void update(StoreDTO storeDTO);
	
	/**
	 * 맛집 삭제
	 * @param storeId
	 */
	void delete(Long storeId);
}
