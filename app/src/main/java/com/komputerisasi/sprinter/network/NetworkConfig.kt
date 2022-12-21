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


    //fungsi get data item by all
    @FormUrlEncoded
    @POST("api/getitembyall")
    fun getDataLike(@Field("queryitem") queryitem: String?,
                    @Field("dbname")globalDatabase: String) : Call<ResultGetItemById>

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


    // fungsi update profil
    @FormUrlEncoded
    @POST("api/updateProfil")
    fun updateDataProfil(
                      @Field("tblnm") tblname : String,
                      @Field("colnm") colname : String,
                      @Field("value") value : String,
                      @Field("dbname") globalDatabase : String) : Call<ResultStatus>


    //Fungsi Get Data User
    @FormUrlEncoded
    @POST("api/getdatauser")
    fun getDataUser(@Field("dbname") globalDatabase : String) : Call<ResultDataUser>

    //Fungsi Get Data Item Sparepart
    @FormUrlEncoded
    @POST("api/getdataitemsparepart")
    fun getDataSparepart(@Field("dbname") globalDatabase : String,
                         @Field("searchquery") datasearch : String,
                         @Field("limit_start") limitstart : String,
                         @Field("limit_end") limitend : String) : Call<ResultDataItemSparepart>

    //Fungsi Get Data Item Kantor
    @FormUrlEncoded
    @POST("api/getdataitemkantor")
    fun getDataKantor(@Field("dbname") globalDatabase : String,
                         @Field("searchquery") datasearch : String,
                         @Field("limit_start") limitstart : String,
                         @Field("limit_end") limitend : String) : Call<ResultDataItemKantor>

    //Fungsi Get Data Item Kantor
    @FormUrlEncoded
    @POST("api/getdataitemkantormasuk")
    fun getDataKantorMasuk(@Field("dbname") globalDatabase : String,
                      @Field("searchquery") datasearch : String,
                      @Field("limit_start") limitstart : String,
                      @Field("limit_end") limitend : String) : Call<ResultDataItemKantorMasuk>

    //Fungsi Get Data Item Kantor
    @FormUrlEncoded
    @POST("api/getdataitemkantorkeluar")
    fun getDataKantorKeluar(@Field("dbname") globalDatabase : String,
                      @Field("searchquery") datasearch : String,
                      @Field("limit_start") limitstart : String,
                      @Field("limit_end") limitend : String) : Call<ResultDataItemKantorKeluar>

    //Fungsi Get Data Item Sparepart
    @FormUrlEncoded
    @POST("api/getdataitemsparepartmasuk")
    fun getDatasparepartmasuk(@Field("dbname") globalDatabase : String,
                      @Field("searchquery") datasearch : String,
                      @Field("limit_start") limitstart : String,
                      @Field("limit_end") limitend : String) : Call<ResultDataItemSparepartMasuk>

    //Fungsi Get Data Item Sparepart
    @FormUrlEncoded
    @POST("api/getdataitemsparepartkeluar")
    fun getDatasparepartKeluar(@Field("dbname") globalDatabase : String,
                              @Field("searchquery") datasearch : String,
                              @Field("limit_start") limitstart : String,
                              @Field("limit_end") limitend : String) : Call<ResultDataItemSparepartKeluar>

    //fungsi get data item by id kantor
    @FormUrlEncoded
    @POST("api/getitembyidkantor")
    fun getitembyidkantor(@Field("itemnos") itemnos: String?,
                    @Field("dbname") globalDatabase : String) : Call<ResultDataItemKantorGetItemById>

    //fungsi get data item by id sparepart
    @FormUrlEncoded
    @POST("api/getitembyidsparepart")
    fun getitembyidsparepart(@Field("itemnos") itemnos: String?,
                          @Field("dbname") globalDatabase : String) : Call<ResultDataItemSparepartGetItemById>


    //Fungsi Update Data Item Sparepart Keluar dan masuk
    @FormUrlEncoded
    @POST("api/updateSparepartKeluar")
    fun updateSparepartKeluar(@Field("idtransaksi") idtransaksi : String,
                       @Field("itemno") itemno : String,
                       @Field("tglcreate") tglcreate : String,
                       @Field("qty") qty : Float,
                       @Field("catatan") catatan : String,
                       @Field("lotnumber") lotnumber : String,
                       @Field("inputminusplus") minusplus : Float,
                       @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("api/updateSparepartMasuk")
    fun updateSparepartMasuk(@Field("idtransaksi") idtransaksi : String,
                              @Field("itemno") itemno : String,
                              @Field("tglcreate") tglcreate : String,
                              @Field("qty") qty : Float,
                              @Field("catatan") catatan : String,
                              @Field("lotnumber") lotnumber : String,
                              @Field("inputminusplus") minusplus : Float,
                              @Field("dbname") globalDatabase : String) : Call<ResultStatus>


    //Fungsi Delete Data Sparepart Keluar dan Masuk
    @FormUrlEncoded
    @POST("api/deleteSparepartKeluar")
    fun deleteSparepartKeluar(@Field("id") id: String?,
                       @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("api/deleteSparepartMasuk")
    fun deleteSparepartMasuk(@Field("id") id: String?,
                              @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Update Data Item Kantor Keluar dan masuk
    @FormUrlEncoded
    @POST("api/updateKantorKeluar")
    fun updateKantorKeluar(@Field("idtransaksi") idtransaksi : String,
                              @Field("itemno") itemno : String,
                              @Field("tglcreate") tglcreate : String,
                              @Field("qty") qty : Float,
                              @Field("catatan") catatan : String,
                              @Field("lotnumber") lotnumber : String,
                              @Field("inputminusplus") minusplus : Float,
                              @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("api/updateKantorMasuk")
    fun updateKantorMasuk(@Field("idtransaksi") idtransaksi : String,
                             @Field("itemno") itemno : String,
                             @Field("tglcreate") tglcreate : String,
                             @Field("qty") qty : Float,
                             @Field("catatan") catatan : String,
                             @Field("lotnumber") lotnumber : String,
                             @Field("inputminusplus") minusplus : Float,
                             @Field("dbname") globalDatabase : String) : Call<ResultStatus>


    //Fungsi Delete Data Kantor Keluar dan Masuk
    @FormUrlEncoded
    @POST("api/deleteKantorKeluar")
    fun deleteKantorKeluar(@Field("id") id: String?,
                              @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("api/deleteKantorMasuk")
    fun deleteKantorMasuk(@Field("id") id: String?,
                             @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Add Data Sparepart Keluar dan Masuk
    @FormUrlEncoded
    @POST("api/addSparepartMasuk")
    fun addSparepartMasuk(@Field("itemno") itemno : String,
                   @Field("tglcreate") tglcreate : String,
                   @Field("qty") qty : Float,
                   @Field("catatan") catatan : String,
                   @Field("lotnumber") lotnumber : String,
                   @Field("inputminusplus") minusplus : Float,
                   @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("api/addSparepartKeluar")
    fun addSparepartKeluar(@Field("itemno") itemno : String,
                          @Field("tglcreate") tglcreate : String,
                          @Field("qty") qty : Float,
                          @Field("catatan") catatan : String,
                          @Field("lotnumber") lotnumber : String,
                          @Field("inputminusplus") minusplus : Float,
                          @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Add Data Kantor Keluar dan Masuk
    @FormUrlEncoded
    @POST("api/addKantorMasuk")
    fun addKantorMasuk(@Field("itemno") itemno : String,
                          @Field("tglcreate") tglcreate : String,
                          @Field("qtyrm") qtyrm : Float,
                          @Field("catatan") catatan : String,
                          @Field("lotnumber") lotnumber : String,
                          @Field("inputminusplus") minusplus : Float,
                          @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    @FormUrlEncoded
    @POST("api/addKantorKeluar")
    fun addKantorKeluar(@Field("itemno") itemno : String,
                           @Field("tglcreate") tglcreate : String,
                           @Field("qty") qty : Float,
                           @Field("catatan") catatan : String,
                           @Field("lotnumber") lotnumber : String,
                           @Field("inputminusplus") minusplus : Float,
                           @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    // Fungsi Add Data Item Kantor
    @POST("api/addKantor")
    fun addKantor(
                        @Field("itemno") itemno : String,
                        @Field("deskripsi") deskripsi : String,
                        @Field("kategoriitem") kategoriitem : String,
                        @Field("qty") qty : Float,
                        @Field("satuan") satuan : String,
                        @Field("harga") harga : String,
                        @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    // Fungsi Add Data Item Kantor
    @POST("api/addSparpeart")
    fun addSparepart(
        @Field("itemno") itemno : String,
        @Field("deskripsi") deskripsi : String,
        @Field("kategoriitem") kategoriitem : String,
        @Field("qty") qty : Float,
        @Field("satuan") satuan : String,
        @Field("harga") harga : String,
        @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi update data item kantor
    @FormUrlEncoded
    @POST("api/updateItemKantor")
    fun updateItemKantor(
                          @Field("itemno") itemno : String,
                          @Field("tglcreate") tglcreate : String,
                          @Field("qty") qty : Float,
                          @Field("catatan") catatan : String,
                          @Field("lotnumber") lotnumber : String,
                          @Field("inputminusplus") minusplus : Float,
                          @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi update data item sparepart
    @FormUrlEncoded
    @POST("api/updateItemSparepart")
    fun updateItemSparepart(
                          @Field("itemno") itemno : String,
                          @Field("tglcreate") tglcreate : String,
                          @Field("qty") qty : Float,
                          @Field("catatan") catatan : String,
                          @Field("lotnumber") lotnumber : String,
                          @Field("inputminusplus") minusplus : Float,
                          @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Delete Data Kantor
    @FormUrlEncoded
    @POST("api/deleteItemKantor")
    fun deleteItemKantor(@Field("id") id: String?,
                           @Field("dbname") globalDatabase : String) : Call<ResultStatus>

    //Fungsi Delete Data Sparepart
    @FormUrlEncoded
    @POST("api/deleteItemSparepart")
    fun deleteItemSparepart(@Field("id") id: String?,
                         @Field("dbname") globalDatabase : String) : Call<ResultStatus>


    //Fungsi get kategori item
    @FormUrlEncoded
    @POST("api/getkategoryitembyall")
    fun getDataKategoryLike(@Field("query") query: String?,
                    @Field("dbname")globalDatabase: String) : Call<ResultGetItemById>

    //Fungsi get Satuan item
    @FormUrlEncoded
    @POST("api/getsatuanitembyall")
    fun getDataSatuanLike(@Field("query") query: String?,
                            @Field("dbname")globalDatabase: String) : Call<ResultGetItemById>
}