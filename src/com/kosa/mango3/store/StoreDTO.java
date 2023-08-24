package com.kosa.mango3.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
	private Long storeId;
	private String storeName;
	private String address;
	private String tel;
	private String storeHour;
	private String location;
	private String foodType;
}
