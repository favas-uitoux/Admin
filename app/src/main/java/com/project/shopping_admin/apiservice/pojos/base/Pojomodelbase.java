package com.project.shopping_admin.apiservice.pojos.base;

import com.google.gson.annotations.Expose;

public class Pojomodelbase {


    @Expose
    private String message;
    @Expose
    private String result;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }




}
