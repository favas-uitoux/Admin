package com.project.shopping_admin.apiservice.pojos.show_dash_gridview;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("dash_slno")
	private String dashSlno;

	@SerializedName("details")
	private String details;

	@SerializedName("type")
	private String type;

	@SerializedName("slno")
	private String slno;


	@SerializedName("display_name")
	private String display_name;

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDashSlno(String dashSlno){
		this.dashSlno = dashSlno;
	}

	public String getDashSlno(){
		return dashSlno;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setSlno(String slno){
		this.slno = slno;
	}

	public String getSlno(){
		return slno;
	}
}