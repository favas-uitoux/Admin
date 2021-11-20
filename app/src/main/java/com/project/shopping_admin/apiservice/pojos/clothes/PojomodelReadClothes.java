
package com.project.shopping_admin.apiservice.pojos.clothes;

import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class PojomodelReadClothes {

    @SerializedName("details")
    private List<Detail> mDetails;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("result")
    private String mResult;

    public List<Detail> getDetails() {
        return mDetails;
    }

    public void setDetails(List<Detail> details) {
        mDetails = details;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
