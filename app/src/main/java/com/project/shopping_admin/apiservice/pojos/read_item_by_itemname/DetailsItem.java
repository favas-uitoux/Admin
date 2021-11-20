package com.project.shopping_admin.apiservice.pojos.read_item_by_itemname;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("fname")
	private String fname;

	@SerializedName("color")
	private String color;

	@SerializedName("size")
	private String size;

	@SerializedName("itemname")
	private String itemname;

	@SerializedName("company")
	private String company;

	@SerializedName("sub_category1")
	private String subCategory1;

	@SerializedName("sub_category2")
	private String subCategory2;

	@SerializedName("category")
	private String category;

	@SerializedName("brand")
	private String brand;

	@SerializedName("stkid_from_server")
	private String stkidFromServer;

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setItemname(String itemname){
		this.itemname = itemname;
	}

	public String getItemname(){
		return itemname;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompany(){
		return company;
	}

	public void setSubCategory1(String subCategory1){
		this.subCategory1 = subCategory1;
	}

	public String getSubCategory1(){
		return subCategory1;
	}

	public void setSubCategory2(String subCategory2){
		this.subCategory2 = subCategory2;
	}

	public String getSubCategory2(){
		return subCategory2;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	public void setStkidFromServer(String stkidFromServer){
		this.stkidFromServer = stkidFromServer;
	}

	public String getStkidFromServer(){
		return stkidFromServer;
	}
}