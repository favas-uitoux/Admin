package com.project.shopping_admin.apiservice.pojos.delete_pic;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("message")
	private String message;

	@SerializedName("slno")
	private String slno;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setSlno(String slno){
		this.slno = slno;
	}

	public String getSlno(){
		return slno;
	}
}