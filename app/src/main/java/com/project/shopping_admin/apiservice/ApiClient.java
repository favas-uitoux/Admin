package com.project.shopping_admin.apiservice;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{

//    public  static  String BASE_URL="https://jsonplaceholder.typicode.com";
//    public  static String BASE_URL="http://nepdata.in/";
  //  public  static  String BASE_URL="https://evmair.com/";

  //  public  static  String BASE_URL="http://neptonglobal.in/";
    public  static String BASE_URL="http://milantex.in/";

    public   static Retrofit retrofit=null;





    public static Retrofit getClient() {
        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }







}
