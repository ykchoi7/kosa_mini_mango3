package com.kosa.mango3.review.dao;

import java.util.List;

import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.review.dto.ReviewDTO;

public interface ReviewDAO {

	/**
	 * 가게 상세정보 안의 해당 가게 리뷰 전체 조회
	 */
	List<ReviewDTO> selectByStoreNo(long storeIdO, int page) throws FindException;
//	SELECT * FROM review WHERE storeId = ?
	
	/**
	 * 가게 상세정보 안의 평가별 리뷰 전체 조회
	 * @return 상품목록
	 * @throws FindException; 저장소에 저장된 상품이 한개도 없으면 예외발생한다.
	 */
	List<ReviewDTO> selectByGrade(long storeId, int grade, int page) throws FindException;
//	SELECT * FROM review WHERE storeId = ? AND grade = ?
	
	/**
	 * 리뷰 쓰기
	 */
	void create(ReviewDTO reviewDTO, String id)  throws AddException;
	
	/**
	 * 리뷰 삭제
	 */
	void delete(long reviewId);
	
	/**
	 * 내 리뷰 리스트 조회
	 */
	List<ReviewDTO> selectByCustomer(String loginId, int page);
	
//	SELECT * FROM review WHERE loginId = ? 
}
