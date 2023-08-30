package com.kosa.mango3.review.service;

import java.util.List;

import com.kosa.mango3.exception.AddException;
import com.kosa.mango3.exception.FindException;
import com.kosa.mango3.exception.RemoveException;
import com.kosa.mango3.review.dao.ReviewDAO;
import com.kosa.mango3.review.dao.ReviewDAOOracle;
import com.kosa.mango3.review.dto.ReviewDTO;

public class ReviewService {
	private ReviewDAO dao;
	
	public ReviewService() {
		this.dao = new ReviewDAOOracle();
	}
	
	public void create(ReviewDTO reviewDTO) throws AddException {
		dao.create(reviewDTO);
	}
	
	public List<ReviewDTO> selectByStoreNo(long storeId, int page) throws FindException {
		return dao.selectByStoreNo(storeId, page);
	}
	
	public List<ReviewDTO> selectByGrade(long storeId, int grade, int page) throws FindException {
		return dao.selectByGrade(storeId, grade, page);
	}
	
	public List<ReviewDTO> selectByCustomer(String loginId, int page) throws FindException {
		return dao.selectByCustomer(loginId, page);
	}
	
	public void delete(long reviewId) throws RemoveException {
		dao.delete(reviewId);
	}
	
	public int countStoreReview(long storeId) throws FindException {
		return dao.countStoreReview(storeId);
	}
	
	public int countGradeReview(long storeId, int grade) throws FindException {
		return dao.countGradeReview(storeId, grade);
	}
	
	public int countMyReview(String loginId) throws FindException {
		return dao.countMyReview(loginId);
	}
	
}
