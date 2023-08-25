package com.kosa.mango3.store;

import java.util.ArrayList;
import java.util.List;

import com.kosa.mango3.review.ReviewDTO;

public class PageTest {
	   List<StoreDTO> sList;
	   List<ReviewDTO> rList;

	   int index;
	   
	   public PageTest(List<StoreDTO> li) {
	      sList=new ArrayList<StoreDTO>();
	      index=0;
	   }
	  
	   public PageTest(ArrayList<ReviewDTO> li, String m) {
		   rList=new ArrayList<ReviewDTO>();
		   index=0;
	   }
	   
	   public void next() {
	      for(int i=index;i<index+2;i++) {
	    	  System.out.println("---------------------------");
	    	  System.out.print(sList.get(i).toString());
	      }
	      index+=2;
	   }
	   
	   public void before() {
	      index-=2;
	      for(int i=index-2;i<index;i++) {
	    	  System.out.println("---------------------------");
			  System.out.print(sList.get(i).toString());
	      }
	   }
	   
	   private void print() {
		   
	   }

}
