package com.kosa.mango3.review.dto;

import com.kosa.mango3.customer.dto.CustomerDTO;
import com.kosa.mango3.store.dto.StoreDTO;

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
public class ReviewDTO {
	private Long reviewId;
	private Integer grade;
	private String comment;
	private String regdate;
	private CustomerDTO customerDTO;
	private StoreDTO storeDTO;
}
