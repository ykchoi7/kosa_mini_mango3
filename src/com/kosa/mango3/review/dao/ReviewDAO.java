package com.kosa.mango3.review.dao;

import java.util.List;

import com.kosa.mango3.review.dto.ReviewDTO;

public class ReviewDAO implements ReviewInterface {

	@Override
	public List<ReviewDTO> selectByStore(int storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewDTO> selectByGrade(int storeId, int grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int reviewId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReviewDTO> selectByCustomer(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}

}
