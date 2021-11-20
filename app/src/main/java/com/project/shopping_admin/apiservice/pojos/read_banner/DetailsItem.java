package com.project.shopping_admin.apiservice.pojos.read_banner;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("dash_slno")
	private String dashSlno;

	@SerializedName("display_name")
	private String displayName;

	public void setDashSlno(String dashSlno){
		this.dashSlno = dashSlno;
	}

	public String getDashSlno(){
		return dashSlno;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public DetailsItem(String dashSlno, String displayName) {
		this.dashSlno = dashSlno;
		this.displayName = displayName;
	}
}