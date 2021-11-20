package com.project.shopping_admin.apiservice.pojos.show_banner;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("dash_slno")
	private String dash_slno;

	public String getDash_slno() {
		return dash_slno;
	}

	public void setDash_slno(String dash_slno) {
		this.dash_slno = dash_slno;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}
}