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
import com.komputerisasi.crud.LoginUtama.Companion.globalVar


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
        if(globalVar.isEmpty()){
            globalVar = "http://localhost/"
        }
        return Retrofit.Builder()
            .baseUrl(globalVar)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(DataService::class.java)

}


interface DataService{

    //Fungsi Login Data
    @FormUrlEncoded
    @POST("api/login")
    fun loginData(@Field("username") username : String,
                  @Field("password") password : String,
                  @Field("dbname") globalDatabase : String ) : Call<ResultLogin>


    //<-------  Warehous FG KELUAR ---->

    //Fungsi Get Data Fg Keluar
    @FormUrlEncoded
    @POST("api/getfgkeluar")

    fun getDataFgKeluar(@Field("dbname") globalDatabase : String) : Call<ResultFgKeluarItem>

    //Fungsi Create Data FG Keluar
    @FormUrlEncoded
    @POST("api/addFgKeluar")
    fun addFgKeluar(@Field("itemno") itemno : String,
                 @Field("tglcreatefg") tglcreatefg : String,
                 @Field("qtyfg") qtyfg : Int,
                 @Field("lotnumber") lotnumber : String,
                 @Field("inputminusplus") minusplus : String,
                 @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data fg keluar
    @FormUrlEncoded
    @POST("api/updateFgKeluar")
    fun updateFgKeluar(@Field("idfgkeluar") idfgkeluar : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreatefg") tglcreatefg : String,
                       @Field("qtyfg") qtyfg : Int,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : String,
                       @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Delete Data fg keluar
    @FormUrlEncoded
    @POST("api/deleteFgKeluar")
    fun deleteFgKeluar(@Field("id") id: String?,
                       @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //<-------  Warehous FG MASUK ---->

    //Fungsi Get Data Fg Masuk
    @FormUrlEncoded
    @POST("api/getfgmasuk")

    fun getDataFgMasuk(@Field("dbname") globalDatabase : String) : Call<ResultFgMasukItem>

    //Fungsi Create Data FG Masuk
    @FormUrlEncoded
    @POST("api/addFgMasuk")
    fun addFgMasuk(@Field("itemno") itemno : String,
                   @Field("tglcreatefg") tglcreatefg : String,
                   @Field("qtyfg") qtyfg : Int,
                   @Field("lotnumber") lotnumber : String,
                   @Field("inputminusplus") minusplus : String,
                   @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data fg Masuk
    @FormUrlEncoded
    @POST("api/updateFgMasuk")
    fun updateFgMasuk(@Field("idfgmasuk") idfgmasuk : String,
                      @Field("itemno") itemno : String,
                      @Field("tglcreatefg") tglcreatefg : String,
                      @Field("qtyfg") qtyfg : Int,
                      @Field("lotnumber") lotnumber : String,
                      @Field("inputminusplus") minusplus : String,
                      @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Delete Data fg masuk
    @FormUrlEncoded
    @POST("api/deleteFgMasuk")
    fun deleteFgMasuk(@Field("id") id: String?,
                      @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //<-------  Warehous RM KELUAR ---->

    //Fungsi Get Data Rm Keluar
    @FormUrlEncoded
    @POST("api/getrmkeluar")

    fun getDataRmKeluar(@Field("dbname") globalDatabase : String) : Call<ResultRmKeluarItem>

    //Fungsi Create Data Rm Keluar
    @FormUrlEncoded
    @POST("api/addRmKeluar")
    fun addRmKeluar(@Field("itemno") itemno : String,
                    @Field("tglcreaterm") tglcreaterm : String,
                    @Field("qtyrm") qtyrm : Int,
                    @Field("lotnumber") lotnumber : String,
                    @Field("inputminusplus") minusplus : String,
                    @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data Rm Keluar
    @FormUrlEncoded
    @POST("api/updateRmKeluar")
    fun updateRmKeluar(@Field("idrmkeluar") idrmkeluar : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreaterm") tglcreaterm : String,
                       @Field("qtyrm") qtyrm : Int,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : String,
                       @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Delete Data Rm Keluar
    @FormUrlEncoded
    @POST("api/deleteRmKeluar")
    fun deleteRmKeluar(@Field("id") id: String?,
                       @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //<-------  Warehous RM MASUK ---->

    //Fungsi Get Data Rm Masuk
    @FormUrlEncoded
    @POST("api/getrmmasuk")

    fun getDataRmMasuk(@Field("dbname") globalDatabase : String) : Call<ResultRmMasukItem>

    //Fungsi Create Data Rm Masuk
    @FormUrlEncoded
    @POST("api/addRmMasuk")
    fun addRmMasuk(@Field("itemno") itemno : String,
                    @Field("tglcreaterm") tglcreaterm : String,
                    @Field("qtyrm") qtyrm : Int,
                    @Field("lotnumber") lotnumber : String,
                    @Field("inputminusplus") minusplus : String,
                   @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data Rm Masuk
    @FormUrlEncoded
    @POST("api/updateRmMasuk")
    fun updateRmMasuk(@Field("idrmmasuk") idrmmasuk : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreaterm") tglcreaterm : String,
                       @Field("qtyrm") qtyrm : Int,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : String,
                      @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Delete Data Rm Masuk
    @FormUrlEncoded
    @POST("api/deleteRmMasuk")
    fun deleteRmMasuk(@Field("id") id: String?,
                      @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //fungsi get data item by id
    @FormUrlEncoded
    @POST("api/getitembyid")
    fun getitembyid(@Field("itemnos") itemnos: String?,
                    @Field("dbname") globalDatabase : String) : Call<ResultGetItemById>
}