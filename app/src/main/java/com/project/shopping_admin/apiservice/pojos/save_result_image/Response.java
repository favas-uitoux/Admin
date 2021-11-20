package com.project.shopping_admin.apiservice.pojos.save_result_image;

import com.google.gson.annotations.Expose;

public class Response {


    @Expose
    private String message;
    @Expose
    private String result;

    @Expose
    private String slno;

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

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
