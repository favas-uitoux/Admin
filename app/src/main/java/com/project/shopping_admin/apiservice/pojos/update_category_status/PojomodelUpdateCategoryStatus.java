package com.project.shopping_admin.apiservice.pojos.update_category_status;

import com.google.gson.annotations.Expose;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;

public class PojomodelUpdateCategoryStatus  extends Pojomodelbase {

    @Expose
    private String slno;


    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }
}
