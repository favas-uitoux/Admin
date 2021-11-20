package com.project.shopping_admin.apiservice;



import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.apiservice.pojos.clothes.PojomodelReadClothes;
import com.project.shopping_admin.apiservice.pojos.read_category.Response;
import com.project.shopping_admin.apiservice.pojos.update_category_status.PojomodelUpdateCategoryStatus;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Endpoint {


 @FormUrlEncoded
 @POST("zpa/read_admin_mobs.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_admin_mob.Response> read_admin_mobs(@Field("apikey") String apikey);



 @FormUrlEncoded
 @POST("zpa/send_sms.php")
 Call<Pojomodelbase> send_sms(@Field("apikey") String apikey,@Field("mob") String mob,@Field("otp") String otp);



 @FormUrlEncoded
 @POST("zpa/read_banner.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_banner.Response> read_banner_types(@Field("apikey") String apikey);



 @FormUrlEncoded
 @POST("zpa/save_banner_item.php")
 Call<Pojomodelbase> save_banner_items(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno, @Field("stkid") String stkid,@Field("image") String image,@Field("display_name") String display_name);





 @FormUrlEncoded
 @POST("zpa/remove_from_banner.php")
 Call<com.project.shopping_admin.apiservice.pojos.show_banner.Response> remove_from_banner(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno, @Field("image") String image,@Field("banner_type") String banner_type);



 @Multipart
 @POST("zpa/images/save_banner.php")
 Call<com.project.shopping_admin.apiservice.pojos.show_banner.Response> save_banner_pic(@Part MultipartBody.Part part, @Part ("apikey") RequestBody apikey, @Part ("banner_type") RequestBody banner_type, @Part ("by") RequestBody by, @Part ("fname") RequestBody fname);




 @FormUrlEncoded
 @POST("zpa/show_banner.php")
 Call<com.project.shopping_admin.apiservice.pojos.show_banner.Response> show_banner(@Field("apikey") String apikey, @Field("banner_type") String banner_type);



 @Multipart
 @POST("zpa/images/save_pic_group.php")
 Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> save_group_pic(@Part MultipartBody.Part part, @Part ("apikey") RequestBody apikey, @Part ("by") RequestBody by,@Part ("display_name") RequestBody display_name,@Part ("dash_slno") RequestBody dash_slno,@Part ("category") RequestBody category,@Part ("sub_category1") RequestBody sub_category1,@Part ("sub_category2") RequestBody sub_category2,@Part ("company") RequestBody company,@Part ("brand") RequestBody brand,@Part ("pattern") RequestBody pattern,@Part ("color") RequestBody color,@Part ("size") RequestBody size, @Part ("fname") RequestBody fname);





 @FormUrlEncoded
 @POST("zpa/read_size.php")
 Call<Response> read_size(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1,@Field("sub_category2") String sub_category2,@Field("company") String company,@Field("brand") String brand,@Field("pattern") String pattern,@Field("color") String color);



 @FormUrlEncoded
 @POST("zpa/read_color.php")
 Call<Response> read_color(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1,@Field("sub_category2") String sub_category2,@Field("company") String company,@Field("brand") String brand,@Field("pattern") String pattern);






 @FormUrlEncoded
 @POST("zpa/read_pattern.php")
 Call<Response> read_pattern(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1,@Field("sub_category2") String sub_category2,@Field("company") String company,@Field("brand") String brand);




 @FormUrlEncoded
 @POST("zpa/read_brand.php")
 Call<Response> read_brand(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1,@Field("sub_category2") String sub_category2,@Field("company") String company);




 @FormUrlEncoded
 @POST("zpa/read_company.php")
 Call<Response> read_company(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1,@Field("sub_category2") String sub_category2);



 @FormUrlEncoded
 @POST("zpa/read_sub2.php")
 Call<Response> read_sub2(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1);



 @FormUrlEncoded
 @POST("zpa/read_category2ad.php")
 Call<Response> read_sub1(@Field("apikey") String apikey,@Field("category") String category);



 @FormUrlEncoded
 @POST("zpa/remove_item_from_dash_category.php")
 Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> remove_item_from_dash_category(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno,@Field("image") String image);




 @FormUrlEncoded
 @POST("zpa/show_dashborad_gridview_ad.php")
 Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> show_dash_gridview(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno);




 @FormUrlEncoded
 @POST("zpa/save_dash_item.php")
 Call<Pojomodelbase> save_dash_items(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno, @Field("stkid") String stkid,@Field("type") String type,@Field("image") String image);



 @FormUrlEncoded
 @POST("zpa/change_category_display_name.php")
 Call<Pojomodelbase> change_display_name_category(@Field("apikey") String apikey, @Field("slno") String slno, @Field("new_display_name") String new_display_name);


 @FormUrlEncoded
 @POST("zpa/category_down.php")
 Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> movedown_category(@Field("apikey") String apikey, @Field("slno") String slno, @Field("order_no_old") String order_no_old, @Field("order_no_new") String order_no_new);




 @FormUrlEncoded
 @POST("zpa/category_up.php")
 Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> moveup_category(@Field("apikey") String apikey, @Field("slno") String slno, @Field("order_no_old") String order_no_old, @Field("order_no_new") String order_no_new);



 @FormUrlEncoded
 @POST("zpa/update_category_status.php")
 Call<PojomodelUpdateCategoryStatus> update_category_status(@Field("apikey") String apikey, @Field("slno") String slno, @Field("status") String status);



 @FormUrlEncoded
 @POST("zpa/read_dashboard_category.php")
 Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> read_dashboard_category(@Field("apikey") String apikey);


 @FormUrlEncoded
 @POST("zpa/add_dahboard_category.php")
 Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> add_dashboard_category(@Field("apikey") String apikey,  @Field("display_name") String display_name);


 @FormUrlEncoded
 @POST("zpa/add_dash_offer_cat.php")
 Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> add_dashboard_offer_cat(@Field("apikey") String apikey,  @Field("display_name") String display_name,@Field("cnt") String cnt,@Field("offer_type") String offer_type);




 @FormUrlEncoded
 @POST("zpa/read_item_by_category.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.Response> read_item_by_category(@Field("apikey") String apikey, @Field ("category") String category,@Field("sub_category1") String sub_category1,@Field("sub_category2") String sub_category2);



 @FormUrlEncoded
 @POST("zpa/read_item_by_itemname.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.Response> read_item_by_itemname(@Field("apikey") String apikey, @Field ("itemname") String itemname);


 @FormUrlEncoded
 @POST("zpa/read_item_by_itemname_under_category.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.Response> read_item_by_itemname_under_category(@Field("apikey") String apikey, @Field ("itemname") String itemname, @Field ("category") String category,@Field ("stkid") String stkid);

 @FormUrlEncoded
 @POST("zpa/get_product_by_barcode_or_itemname.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.Response> get_product_by_barcode_or_itemname(@Field("apikey") String apikey, @Field ("itemname") String itemname,@Field ("type") String type);


 @FormUrlEncoded
 @POST("zpa/images/delete_pic.php")
 Call<com.project.shopping_admin.apiservice.pojos.delete_pic.Response> delete_pic(@Field("apikey") String apikey, @Field ("stockid") String stockid, @Field ("slno") String slno);


 @Multipart
 @POST("zpa/images/save_pic_item.php")
 Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> save_item_pic(@Part MultipartBody.Part part, @Part ("stockid") RequestBody stockid, @Part ("by") RequestBody by, @Part ("fname") RequestBody fname, @Part ("apikey") RequestBody apikey);



 @FormUrlEncoded
 @POST("zpa/read_category1_ad.php")
 Call<Response> read_category1(@Field("apikey") String apikey);


 @FormUrlEncoded
 @POST("zpa/read_item_by_stock_id.php")
 Call<com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.Response> read_item_by_stkid(@Field("apikey") String apikey, @Field("stkid") String stkid);




 @FormUrlEncoded
 @POST("zpa/read_category3_ad.php")
 Call<Response> read_category3(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1);







}
