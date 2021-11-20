package com.project.shopping_admin;

public class Utils {


    public static String GVCOT(String search_s) {
        if (search_s == null) {

        } else {
            search_s = "'" + search_s.replaceAll("'", "''") + "'";
        }

        return search_s;
    }


}
