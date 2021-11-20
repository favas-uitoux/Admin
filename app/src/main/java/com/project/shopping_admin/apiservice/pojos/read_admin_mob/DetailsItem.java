package com.project.shopping_admin.apiservice.pojos.read_admin_mob;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("mob")
	private String mob;

	public void setMob(String mob){
		this.mob = mob;
	}

	public String getMob(){
		return mob;
	}
}