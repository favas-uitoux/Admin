package com.project.shopping_admin.model;

public class model_img_and_stkid {

    private String image;

    private String stkid;


    public model_img_and_stkid(String image, String stkid) {
        this.image = image;
        this.stkid = stkid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStkid() {
        return stkid;
    }

    public void setStkid(String stkid) {
        this.stkid = stkid;
    }
}
