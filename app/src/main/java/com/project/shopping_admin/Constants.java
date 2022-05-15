package com.project.shopping_admin;

import com.project.shopping_admin.interfaces.AdapterPage2Interface;
import com.project.shopping_admin.interfaces.AdditemActivitytInterface;
import com.project.shopping_admin.interfaces.DisplayActivityInterface;
import com.project.shopping_admin.interfaces.Frag1Interface;
import com.project.shopping_admin.interfaces.Frag2Interface;
import com.project.shopping_admin.interfaces.Frag3Interface;
import com.project.shopping_admin.interfaces.SetBannerInterface;
import com.project.shopping_admin.interfaces.SetCategoryFragmentInterface;
import com.project.shopping_admin.interfaces.SetItemsFragmentInterface;

public class Constants {

    public static final int TAKE_PIC = 300;

    public static String Buy_Qty_Free_Qty="Buy Qty Free Qty";
    public static String Buy_Qty_Free_Percent="Buy Qty Free Percent";
    public static String Offer="Offer";
    public static String Today_Offer="Today Offer";

    public static String Choose_sub_category1="Choose sub category1";
    public static String Choose_sub_category2="Choose sub category2";
    public static String Choose_Company="Choose Company";
    public static String Brand="Brand";
    public static String Pattern="Pattern";

    public static String Color="Color";
    public static String Size="Size";

    public static String SelectDashbboardCategory="Select Dashboard Category";
    public static String Parcel1="Parcel1";

    public static String dash_slno="dash_slno";
    public static String Dashboard_category="Dashboard_category";
    public static String item="item";
    public static String SetPhoto="Set Photo";
    public static String BestBuy="BestBuy";
    public static String SetDash="Set Dashboard";

    public static String SelectMainCategory="Select Main Category";
    public static String SelectSubCategory="Select Sub Category1";
    public static String SelectSubCategory2="Select Sub Category2";
    public static String api_key="200";

    public static Frag1Interface frag1Interface=null;
    public static String Choose_Brand ="Choose Brand";
    public static String Choose_Pattern="Choose Pattern";
    public static String Choose_Color="Choose Color";
    public static String Choose_Size="Choose Size";

    private static  String random_no="";

    private static AdapterPage2Interface adapterPage2Interface=null;


    private   static Frag2Interface page2FragInterface=null;

    private   static Frag3Interface frag3Interface=null;


    private static SetCategoryFragmentInterface setCategoryFragmentInterface=null;


    private static String tick_slno="";

    private static SetItemsFragmentInterface setItemsFragmentInterface=null;

    private static DisplayActivityInterface displayActivityInterface=null;

    private static AdditemActivitytInterface additemActivitytInterface=null;


    private  static  String dash_sl="";



    private static SetBannerInterface setBannerInterface=null;

    public static SetBannerInterface getSetBannerInterface() {
        return setBannerInterface;
    }

    public static void setSetBannerInterface(SetBannerInterface setBannerInterface) {
        Constants.setBannerInterface = setBannerInterface;
    }

    public static String getDash_sl() {
        return dash_sl;
    }

    public static void setDash_sl(String dash_sl) {
        Constants.dash_sl = dash_sl;
    }

    public static AdditemActivitytInterface getAdditemActivitytInterface() {
        return additemActivitytInterface;
    }

    public static void setAdditemActivitytInterface(AdditemActivitytInterface additemActivitytInterface) {
        Constants.additemActivitytInterface = additemActivitytInterface;
    }

    public static DisplayActivityInterface getDisplayActivityInterface() {
        return displayActivityInterface;
    }

    public static void setDisplayActivityInterface(DisplayActivityInterface displayActivityInterface) {
        Constants.displayActivityInterface = displayActivityInterface;
    }

    public static SetItemsFragmentInterface getSetItemsFragmentInterface() {
        return setItemsFragmentInterface;
    }

    public static void setSetItemsFragmentInterface(SetItemsFragmentInterface setItemsFragmentInterface) {
        Constants.setItemsFragmentInterface = setItemsFragmentInterface;
    }

    public static String getTick_slno() {
        return tick_slno;
    }

    public static void setTick_slno(String tick_slno) {
        Constants.tick_slno = tick_slno;
    }

    public static SetCategoryFragmentInterface getSetCategoryFragmentInterface() {
        return setCategoryFragmentInterface;
    }

    public static void setSetCategoryFragmentInterface(SetCategoryFragmentInterface setCategoryFragmentInterface) {
        Constants.setCategoryFragmentInterface = setCategoryFragmentInterface;
    }

    public static Frag3Interface getFrag3Interface() {
        return frag3Interface;
    }

    public static void setFrag3Interface(Frag3Interface frag3Interface) {
        Constants.frag3Interface = frag3Interface;
    }

    private static String from="";

    public static String getFrom() {
        return from;
    }

    public static void setFrom(String from) {
        Constants.from = from;
    }

    public static Frag2Interface getPage2FragInterface() {
        return page2FragInterface;
    }

    public static void setPage2FragInterface(Frag2Interface page2FragInterface) {
        Constants.page2FragInterface = page2FragInterface;
    }



    public static void setAdapterPage2Interface(AdapterPage2Interface adapterPage2Interface) {
        Constants.adapterPage2Interface = adapterPage2Interface;
    }

    public static String getRandom_no() {
        return random_no;
    }

    public static void setRandom_no(String random_no) {
        Constants.random_no = random_no;
    }

    public static Frag1Interface getFrag1Interface() {
        return frag1Interface;
    }

    public static void setFrag1Interface(Frag1Interface frag1Interface) {
        Constants.frag1Interface = frag1Interface;
    }



}
