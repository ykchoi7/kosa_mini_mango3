package com.kosa.mango3.store;

import java.util.ArrayList;
import java.util.List;

public class PageTest {
	   StoreDAO dao = new StoreDAO();
	   List<StoreDTO> list;
	   int index;
	   
	   public PageTest() {
	      list=dao.findByLocation();
	      index=0;
	   }
	   
//	   public void add(int a) {
//	      list.add(a);
//	   }
	   
	   public void next() {
	      for(int i=index;i<index+2;i++) {
	    	  System.out.println("---------------------------");
	    	  System.out.print(list.get(i).getStoreName() + " - ");
			  System.out.println("★".repeat((int)list.get(i).getGrade()) + " (" + list.get(i).getReviewId() +")");
			  System.out.println("위치 : " + list.get(i).getLocation());
			  System.out.println("음식 종류 : " + list.get(i).getFoodType());
	      }
	      index+=2;
	   }
	   
	   public void before() {
	      index-=2;
	      for(int i=index-2;i<index;i++) {
	    	  System.out.println("---------------------------");
			  System.out.print(list.get(i).getStoreName() + " - ");
			  System.out.println("★".repeat((int)list.get(i).getGrade()) + " (" + list.get(i).getReviewId() +")");
			  System.out.println("위치 : " + list.get(i).getLocation());
			  System.out.println("음식 종류 : " + list.get(i).getFoodType());
	      }
	   }

}
