package com.kosa.mango3.review.dao;

import java.util.List;

import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.RemoveException;
import com.kosa.mango3.review.dto.ReviewDTO;

public interface ReviewDAO {

	/**
	 * 리뷰 쓰기
	 * @param reviewDTO
	 * @throws AddException 
	 */
	void create(ReviewDTO reviewDTO) throws AddException;
	
	/**
	 * 가게 상세정보 안의 해당 가게 리뷰 전체 조회
	 * @param storeIdO
	 * @param page
	 * @return
	 * @throws FindException
	 */
	List<ReviewDTO> selectByStoreNo(long storeIdO, int page) throws FindException;
	
	/**
	 * 가게 상세정보 안의 평가별 리뷰 전체 조회
	 * @param storeId
	 * @param grade
	 * @param page
	 * @return
	 * @throws FindException 저장소에 저장된 상품이 한개도 없으면 예외 발생
	 */
	List<ReviewDTO> selectByGrade(long storeId, int grade, int page) throws FindException;
		
	/**
	 * 내 리뷰 리스트 조회
	 * @param loginId
	 * @param page
	 * @return
	 * @throws FindException
	 */
	List<ReviewDTO> selectByCustomer(String loginId, int page) throws FindException;
	
	/**
	 * 리뷰 삭제
	 * @param reviewId
	 * @throws RemoveException
	 */
	void delete(long reviewId) throws RemoveException;
	
	/**
	 * 가게 리뷰 총 갯수
	 * @param storeId
	 * @return
	 * @throws FindException
	 */
	int countStoreReview(long storeId) throws FindException;
	
	/**
	 * 가게 등급별 리뷰 총 갯수
	 * @param storeId
	 * @param grade
	 * @return
	 * @throws FindException
	 */
	int countGradeReview(long storeId, int grade) throws FindException;
	
	/**
	 * 내 리뷰 총 갯수
	 * @param loginId
	 * @return
	 * @throws FindException
	 */
	int countMyReview(String loginId) throws FindException;
}
