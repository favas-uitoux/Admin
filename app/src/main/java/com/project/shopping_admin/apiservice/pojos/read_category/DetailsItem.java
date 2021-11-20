package com.project.shopping_admin.apiservice.pojos.read_category;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("category")
	private String category;

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

//	@Override
// 	public String toString(){
//		return
//
//			 category ;
//		}
}