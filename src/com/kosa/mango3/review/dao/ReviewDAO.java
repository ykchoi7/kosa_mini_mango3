package com.kosa.mango3.review.dao;

import java.util.List;

import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.RemoveException;
import com.kosa.mango3.review.dto.ReviewDTO;

public interface ReviewDAO {

	/**
	 * 리뷰 등록
	 * @param reviewDTO 리뷰 객체
	 * @throws AddException DB연결에 실패한 경우 발생
	 */
	void create(ReviewDTO reviewDTO) throws AddException;
	
	/**
	 * 음식점별 리뷰 전체 조회
	 * @param storeIdO 음식점 일련번호
	 * @param page 페이지
	 * @return 리뷰 리스트
	 * @throws FindException 음식점 일련번호가 없거나 DB연결에 실패한 경우 발생
	 */
	List<ReviewDTO> selectByStoreNo(long storeIdO, int page) throws FindException;
	
	/**
	 * 음식점의 등급별 리뷰 조회
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
	 * 내 리뷰 삭제
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
