
package com.project.shopping_admin.apiservice.pojos.clothes;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Detail {

    @SerializedName("name")
    private String mName;
    @SerializedName("slno")
    private String mSlno;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSlno() {
        return mSlno;
    }

    public void setSlno(String slno) {
        mSlno = slno;
    }

}
