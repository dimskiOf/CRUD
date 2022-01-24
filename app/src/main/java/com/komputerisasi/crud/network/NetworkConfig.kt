package com.komputerisasi.crud.network

import com.komputerisasi.crud.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    //Retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.244/ci3/rest/serverapi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getService() = getRetrofit().create(StaffService::class.java)
}
interface StaffService{
    //Fungsi Login Data
    @FormUrlEncoded
    @POST("login")
    fun loginData(@Field("username") username : String,
                  @Field("password") password : String) : Call<ResultLogin>

    //<-------  Warehous FG KELUAR ---->

    //Fungsi Get Data Fg Keluar
    @GET("getDataFgKeluar")

    fun getDataFgKeluar() : Call<ResultFgKeluarItem>

    //Fungsi Create Data FG Keluar
    @FormUrlEncoded
    @POST("addFgKeluar")
    fun addFgKeluar(@Field("itemno") itemno : String,
                 @Field("tglcreatefg") tglcreatefg : String,
                 @Field("qtyfg") qtyfg : Int,
                 @Field("loadnumber") loadnumber : String,
                 @Field("inputminusplus") minusplus : String) : Call<ResultStatus>

    //Fungsi Update Data fg keluar
    @FormUrlEncoded
    @POST("updateFgKeluar")
    fun updateFgKeluar(@Field("idfgkeluar") idfgkeluar : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreatefg") tglcreatefg : String,
                       @Field("qtyfg") qtyfg : Int,
                       @Field("loadnumber") loadnumber : String,
                       @Field("inputminusplus") minusplus : String) : Call<ResultStatus>

    //Fungsi Delete Data fg keluar
    @FormUrlEncoded
    @POST("deleteFgKeluar")
    fun deleteFgKeluar(@Field("id") id: String?) : Call<ResultStatus>

    //<-------  Warehous FG MASUK ---->

    //Fungsi Get Data Fg Masuk
    @GET("getDataFgMasuk")

    fun getDataFgMasuk() : Call<ResultFgMasukItem>

    //Fungsi Create Data FG Masuk
    @FormUrlEncoded
    @POST("addFgMasuk")
    fun addFgMasuk(@Field("itemno") itemno : String,
                   @Field("tglcreatefg") tglcreatefg : String,
                   @Field("qtyfg") qtyfg : Int,
                   @Field("loadnumber") loadnumber : String,
                   @Field("inputminusplus") minusplus : String) : Call<ResultStatus>

    //Fungsi Update Data fg Masuk
    @FormUrlEncoded
    @POST("updateFgMasuk")
    fun updateFgMasuk(@Field("idfgmasuk") idfgmasuk : String,
                      @Field("itemno") itemno : String,
                      @Field("tglcreatefg") tglcreatefg : String,
                      @Field("qtyfg") qtyfg : Int,
                      @Field("loadnumber") loadnumber : String,
                      @Field("inputminusplus") minusplus : String) : Call<ResultStatus>

    //Fungsi Delete Data fg masuk
    @FormUrlEncoded
    @POST("deleteFgMasuk")
    fun deleteFgMasuk(@Field("id") id: String?) : Call<ResultStatus>

    //<-------  Warehous RM KELUAR ---->

    //Fungsi Get Data Rm Keluar
    @GET("getDataRmKeluar")

    fun getDataRmKeluar() : Call<ResultRmKeluarItem>

    //Fungsi Create Data Rm Keluar
    @FormUrlEncoded
    @POST("addRmKeluar")
    fun addRmKeluar(@Field("itemno") itemno : String,
                    @Field("tglcreaterm") tglcreaterm : String,
                    @Field("qtyrm") qtyrm : Int,
                    @Field("loadnumber") loadnumber : String,
                    @Field("inputminusplus") minusplus : String) : Call<ResultStatus>

    //Fungsi Update Data Rm Keluar
    @FormUrlEncoded
    @POST("updateRmKeluar")
    fun updateRmKeluar(@Field("idrmkeluar") idrmkeluar : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreaterm") tglcreaterm : String,
                       @Field("qtyrm") qtyrm : Int,
                       @Field("loadnumber") loadnumber : String,
                       @Field("inputminusplus") minusplus : String) : Call<ResultStatus>

    //Fungsi Delete Data Rm Keluar
    @FormUrlEncoded
    @POST("deleteRmKeluar")
    fun deleteRmKeluar(@Field("id") id: String?) : Call<ResultStatus>
}