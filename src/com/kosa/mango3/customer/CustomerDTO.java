package com.kosa.mango3.customer;

import java.sql.Date;

import com.kosa.mango3.store.StoreDTO;

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
public class CustomerDTO {
	private String loginId;
	private String pwd;
}
