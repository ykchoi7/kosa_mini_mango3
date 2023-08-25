package com.kosa.mango3.store.admin;

import java.util.List;

import com.kosa.mango3.db.MySql;
import com.kosa.mango3.store.StoreDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdminStoreDAO implements AdminStoreInterface {
//	private Oracle db;
	private MySql db;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StoreDTO> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StoreDTO storeDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long storeId) {
		// TODO Auto-generated method stub

	}

}
