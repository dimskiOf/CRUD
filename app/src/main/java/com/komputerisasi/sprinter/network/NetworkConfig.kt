package com.komputerisasi.sprinter.network

import android.content.Context
import com.komputerisasi.sprinter.BuildConfig
import com.komputerisasi.sprinter.LoginUtama.Companion.globalVar
import com.komputerisasi.sprinter.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


object NetworkConfig {
    val userAgent = "SPRINTER APP"
    fun getInterceptor(context: Context) : OkHttpClient {

        val logging = HttpLoggingInterceptor()

        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        }
        else {
            HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .setCookieStore(context)
            .addInterceptor(logging)
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                  //  .header("Cookie", "application/json, text/javascript, */*; q=0.01")
                  //  .header("Accept-Encoding", "gzip, deflate, br")
                    .header("User-Agent", userAgent)
                    .build()
                chain.proceed(request)
            }
            .cache(null)
            //.cookieJar(JavaNetCookieJar(cookieHandler))
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return okHttpClient
    }

    fun <T> getService(context: Context, baseUrl: String, service: Class<T>): T {
        if (globalVar.isEmpty()){
            globalVar = "http://localhost/"
        }else{
            if ("http://" in globalVar){
            globalVar
            }else{
             globalVar = "http://"+globalVar+"/"
            }
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(globalVar)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor(context))
            .build()

       return retrofit.create(service)
    }
}


interface DataService{
    @FormUrlEncoded
    @POST("api/pingapi")
    fun pingapi(@Field ("apikey") apikey : String,
                @Field ("dbname") globalDatabase : String) : Call<ResultPingApi>

    //Fungsi Login Data
    @FormUrlEncoded
    @POST("api/authdb")
    fun chooseDatabase(@Field ("dbname") database : String) : Call<ResultDBConnect>

    //Fungsi Login Data
    @FormUrlEncoded
    @POST("api/login")
    fun loginData(@Field("username") username : String,
                  @Field("password") password : String,
                  @Field ("dbname") globalDatabase : String) : Call<ResultLogin>


    //<-------  Warehous FG KELUAR ---->

    //Fungsi Get Data Fg Keluar
    @FormUrlEncoded
    @POST("api/getfgkeluar")

    fun getDataFgKeluar(@Field("dbname") globalDatabase : String,
                        @Field("searchquery") datasearch : String,
                        @Field("limit_start") limitstart : String,
                        @Field("limit_end") limitend : String) : Call<ResultFgKeluarItem>

    //Fungsi Create Data FG Keluar
    @FormUrlEncoded
    @POST("api/addFgKeluar")
    fun addFgKeluar(@Field("itemno") itemno : String,
                 @Field("tglcreatefg") tglcreatefg : String,
                 @Field("qtyfg") qtyfg : Float,
                 @Field("catatan") catatanfg : String,
                 @Field("lotnumber") lotnumber : String,
                 @Field("inputminusplus") minusplus : Float,
                 @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data fg keluar
    @FormUrlEncoded
    @POST("api/updateFgKeluar")
    fun updateFgKeluar(@Field("idfgkeluar") idfgkeluar : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreatefg") tglcreatefg : String,
                       @Field("qtyfg") qtyfg : Float,
                       @Field("catatan") catatanfg : String,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : Float,
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

    fun getDataFgMasuk(@Field("dbname") globalDatabase : String,
                       @Field("searchquery") datasearch : String,
                       @Field("limit_start") limitstart : String,
                       @Field("limit_end") limitend : String) : Call<ResultFgMasukItem>

    //Fungsi Create Data FG Masuk
    @FormUrlEncoded
    @POST("api/addFgMasuk")
    fun addFgMasuk(@Field("itemno") itemno : String,
                   @Field("tglcreatefg") tglcreatefg : String,
                   @Field("qtyfg") qtyfg : Float,
                   @Field("catatan") catatanfg : String,
                   @Field("lotnumber") lotnumber : String,
                   @Field("inputminusplus") minusplus : Float,
                   @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data fg Masuk
    @FormUrlEncoded
    @POST("api/updateFgMasuk")
    fun updateFgMasuk(@Field("idfgmasuk") idfgmasuk : String,
                      @Field("itemno") itemno : String,
                      @Field("tglcreatefg") tglcreatefg : String,
                      @Field("qtyfg") qtyfg : Float,
                      @Field("catatan") catatanfg : String,
                      @Field("lotnumber") lotnumber : String,
                      @Field("inputminusplus") minusplus : Float,
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

    fun getDataRmKeluar(@Field("dbname") globalDatabase : String,
                        @Field("searchquery") datasearch : String,
                        @Field("limit_start") limitstart : String,
                        @Field("limit_end") limitend : String) : Call<ResultRmKeluarItem>

    //Fungsi Create Data Rm Keluar
    @FormUrlEncoded
    @POST("api/addRmKeluar")
    fun addRmKeluar(@Field("itemno") itemno : String,
                    @Field("tglcreaterm") tglcreaterm : String,
                    @Field("qtyrm") qtyrm : Float,
                    @Field("catatan") catatanrm : String,
                    @Field("lotnumber") lotnumber : String,
                    @Field("inputminusplus") minusplus : Float,
                    @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data Rm Keluar
    @FormUrlEncoded
    @POST("api/updateRmKeluar")
    fun updateRmKeluar(@Field("idrmkeluar") idrmkeluar : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreaterm") tglcreaterm : String,
                       @Field("qtyrm") qtyrm : Float,
                       @Field("catatan") catatanrm : String,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : Float,
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

    fun getDataRmMasuk(@Field("dbname") globalDatabase : String,
                       @Field("searchquery") datasearch : String,
                       @Field("limit_start") limitstart : String,
                       @Field("limit_end") limitend : String) : Call<ResultRmMasukItem>

    //Fungsi Create Data Rm Masuk
    @FormUrlEncoded
    @POST("api/addRmMasuk")
    fun addRmMasuk(@Field("itemno") itemno : String,
                    @Field("tglcreaterm") tglcreaterm : String,
                    @Field("qtyrm") qtyrm : Float,
                    @Field("catatan") catatanfg : String,
                    @Field("lotnumber") lotnumber : String,
                    @Field("inputminusplus") minusplus : Float,
                    @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data Rm Masuk
    @FormUrlEncoded
    @POST("api/updateRmMasuk")
    fun updateRmMasuk(@Field("idrmmasuk") idrmmasuk : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreaterm") tglcreaterm : String,
                       @Field("qtyrm") qtyrm : Float,
                       @Field("catatan") catatanfg : String,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : Float,
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

    //Fungsi Get Data STOK GUDANG
    @FormUrlEncoded
    @POST("api/getstokitem")
    fun getDataStokItem(@Field("dbname") globalDatabase : String,
                        @Field("searchquery") datasearch : String,
                        @Field("limit_start") limitstart : String,
                        @Field("limit_end") limitend : String) : Call<ResultStok>

    @FormUrlEncoded
    @POST("api/getstokitemfg")
    fun getDataStokItemfg(@Field("dbname") globalDatabase : String,
                          @Field("searchquery") datasearch : String,
                          @Field("limit_start") limitstart : String,
                          @Field("limit_end") limitend : String) : Call<ResultStok>

    @FormUrlEncoded
    @POST("api/getitembyscan")
    fun getScanDataStokItem(
        @Field("itemnos") itemnos: String?,
        @Field("dbname") globalDatabase : String) : Call<ResultCheckStok>
}