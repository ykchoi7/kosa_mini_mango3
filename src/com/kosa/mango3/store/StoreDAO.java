package com.kosa.mango3.store;

import java.util.List;

import com.kosa.mango3.review.dao.ReviewDAO;
import com.kosa.mango3.review.dto.ReviewInterface;

public class StoreDAO implements StoreInterface {
	private ReviewInterface rd;
	
	public StoreDAO(ReviewInterface rd) {
		this.rd = (ReviewDAO) rd;
	}
	
	@Override
	public List<StoreDTO> findByName(String storeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreDTO> findByLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreDTO> findByType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeInfo() {
		// TODO Auto-generated method stub
//		rd.create();
	}

}
