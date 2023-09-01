package com.kosa.mango3.store.dto;

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
	
	//store 기본 변수 
	private long storeId;
	private String storeName;
	private String address;
	private String tel;
	private String storeHour;
	private String location;
	private String foodType;
	
	//리뷰 끌어올때 필요한 변수 
	private long grade;
	private long reviewId;
	private String rwcomment;
	private long reviewCnt;
	
	public String toString() {
		String result="";
		result+=getStoreName() + " - ";
		result+="★".repeat((int)getGrade()) + " (" + getReviewId() +")\n";
		result+="위치 : " + getLocation();
		result+="음식 종류 : " + getFoodType();
		
		return result;
	}
	
}
