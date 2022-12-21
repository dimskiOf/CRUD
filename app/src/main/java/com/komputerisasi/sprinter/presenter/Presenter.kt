package com.komputerisasi.sprinter.presenter

import android.content.Context
import android.util.Log
import com.komputerisasi.sprinter.LoginUtama
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.network.DataService
import com.komputerisasi.sprinter.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response


class Presenter (val crudView: CrudView){

    //get data login
    fun loginData(context:Context, username : String, password : String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .loginData(username, password,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultLogin>{
                override fun onFailure(call: Call<ResultLogin>, t: Throwable) {
                    crudView.onFailedGetLogin(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultLogin>, response: Response<ResultLogin>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.logins
                            crudView.onSuccessGetLogin(data)
                        } else{
                            crudView.onFailedGetLogin(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

    //Fungsi GetDataFgKeluar
    fun getDataFgKeluar(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataFgKeluar(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultFgKeluarItem>{
                override fun onFailure(call: Call<ResultFgKeluarItem>, t: Throwable) {
                    crudView.onFailedGetFgKeluar(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultFgKeluarItem>, response: Response<ResultFgKeluarItem>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.FgKeluarItems
                            crudView.onSuccessGetFgKeluar(data)
                        } else{
                               crudView.onFailedGetFgKeluar(
                                   response.body()?.pesan ?: "Error $status"
                               )
                        }
                    }
                }

            })
    }


    //Add data FG keluar
    fun addDataFgKeluar(context:Context,itemno : String, tglcreate : String, qty : Float, catatan: String , loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addFgKeluar(itemno, tglcreate, qty,catatan,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAddFgKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAddFgKeluar(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAddFgKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Update Data fg keluar
    fun updateDataFgKeluar(context:Context,idfgkeluar: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateFgKeluar(idfgkeluar,itemno,tglcreate,qty, catatan,loadnumber, inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateFgKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateFgKeluar(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateFgKeluar(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //Hapus Data FG Keluar
    fun hapusDataFgKeluar(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteFgKeluar(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteFgKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteFgKeluar(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteFgKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Fungsi GetDataFgMasuk
    fun getDataFgMasuk(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataFgMasuk(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultFgMasukItem>{
                override fun onFailure(call: Call<ResultFgMasukItem>, t: Throwable) {
                    crudView.onFailedGetFgMasuk(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultFgMasukItem>, response: Response<ResultFgMasukItem>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.FgMasukItems
                            crudView.onSuccessGetFgMasuk(data)
                        } else{
                            crudView.onFailedGetFgMasuk(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }


    //Add data FG Masuk
    fun addDataFgMasuk(context:Context,itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addFgMasuk(itemno, tglcreate, qty,catatan,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAddFgMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAddFgMasuk(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAddFgMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Update Data fg Masuk
    fun updateDataFgMasuk(context:Context,idfgmasuk: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateFgMasuk(idfgmasuk,itemno,tglcreate,qty,catatan,loadnumber, inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateFgMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateFgMasuk(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateFgMasuk(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //Hapus Data FG Masuk
    fun hapusDataFgMasuk(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteFgMasuk(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteFgMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteFgMasuk(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteFgMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Fungsi GetDataRmKeluar
    fun getDataRmKeluar(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataRmKeluar(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultRmKeluarItem>{
                override fun onFailure(call: Call<ResultRmKeluarItem>, t: Throwable) {
                    crudView.onFailedGetRmKeluar(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultRmKeluarItem>, response: Response<ResultRmKeluarItem>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.RmKeluarItems
                            crudView.onSuccessGetRmKeluar(data)
                        } else{
                            crudView.onFailedGetRmKeluar(response.body()?.pesan ?:"Error $status")
                        }
                    }
                }

            })
    }


    //Add data RM keluar
    fun addDataRmKeluar(context:Context,itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addRmKeluar(itemno, tglcreate, qty,catatan,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAddRmKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAddRmKeluar(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAddRmKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Update Data Rm Keluar
    fun updateDataRmKeluar(context:Context,idrmkeluar: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateRmKeluar(idrmkeluar,itemno,tglcreate,qty,catatan,loadnumber, inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateRmKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateRmKeluark(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateRmKeluar(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //Hapus Data RM Keluar
    fun hapusDataRmKeluar(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteRmKeluar(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteRmKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteRmKeluar(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteRmKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Fungsi GetDataRmMasuk
    fun getDataRmMasuk(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataRmMasuk(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultRmMasukItem>{
                override fun onFailure(call: Call<ResultRmMasukItem>, t: Throwable) {
                    crudView.onFailedGetRmMasuk(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultRmMasukItem>, response: Response<ResultRmMasukItem>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.RmMasukItems
                            crudView.onSuccessGetRmMasuk(data)
                        } else{
                            crudView.onFailedGetRmMasuk(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }


    //Add data RM Masuk
    fun addDataRmMasuk(context:Context,itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addRmMasuk(itemno, tglcreate, qty,catatan,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAddRmMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAddRmMasuk(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAddRmMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //Update Data Rm Masuk
    fun updateDataRmMasuk(context:Context,idrmmasuk: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateRmMasuk(idrmmasuk,itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateRmMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateRmMasuk(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateRmMasuk(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //Hapus Data RM Masuk
    fun hapusDataRmMasuk(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteRmMasuk(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteRmMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteRmMasuk(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteRmMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    // get data item by id
    fun getDataItemById(context:Context,itemnos: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getitembyid(itemnos,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultGetItemById>{
                override fun onFailure(call: Call<ResultGetItemById>, t: Throwable) {
                    crudView.onErrorGetItemById(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultGetItemById>, response: Response<ResultGetItemById>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.GetItemByIds
                            crudView.onSuccessGetItemById(data)
                        } else{
                            crudView.onErrorGetItemById(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
        }

    // get data item by search
    fun getDataItemBySearch(context:Context,queryitem: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataLike(queryitem,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultGetItemById>{
                override fun onFailure(call: Call<ResultGetItemById>, t: Throwable) {
                    crudView.onErrorGetItemQuery(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultGetItemById>, response: Response<ResultGetItemById>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.GetItemByIds
                            crudView.onSuccessGetItemQuery(data)
                        } else{
                            crudView.onErrorGetItemQuery(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

    // get data dbname by id
    fun DbName(context:Context,dbids: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).chooseDatabase(dbids.toString())
            .enqueue(object : retrofit2.Callback<ResultDBConnect>{
                override fun onFailure(call: Call<ResultDBConnect>, t: Throwable) {
                    crudView.onErrorGetDBname(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDBConnect>, response: Response<ResultDBConnect>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.pesan

                            crudView.onSuccessGetDBname(data.toString())
                        } else{
                            crudView.onErrorGetDBname(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

    fun Pingapi(context:Context,ApiKey:String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).pingapi(ApiKey.toString(),LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultPingApi>{
                override fun onFailure(call: Call<ResultPingApi>, t: Throwable) {
                    crudView.onErrorPingApi(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultPingApi>, response: Response<ResultPingApi>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.pesan

                            crudView.onSuccessPingApi(data.toString())
                        } else{
                            crudView.onErrorPingApi(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

    //Fungsi GetDataStok
    fun getDataStokItem(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataStokItem(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultStok>{
                override fun onFailure(call: Call<ResultStok>, t: Throwable) {
                    crudView.onFailedGetChekStok(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultStok>, response: Response<ResultStok>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultStok
                            crudView.onSuccessGetChekStok(data)
                        } else{
                            crudView.onFailedGetChekStok(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    fun getDataStokItemFG(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataStokItemfg(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultStok>{
                override fun onFailure(call: Call<ResultStok>, t: Throwable) {
                    crudView.onFailedGetChekStok(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultStok>, response: Response<ResultStok>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultStok
                            crudView.onSuccessGetChekStok(data)
                        } else{
                            crudView.onFailedGetChekStok(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //Fungsi ScannGetDataStok
    fun getScanDataStokItem(context:Context,itemnos: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getScanDataStokItem(itemnos,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultCheckStok>{
                override fun onFailure(call: Call<ResultCheckStok>, t: Throwable) {
                    crudView.onFailedGetScanChekStok(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultCheckStok>, response: Response<ResultCheckStok>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ChekStokItem
                            crudView.onSuccessGetScanChekStok(data)
                        } else{
                            crudView.onFailedGetScanChekStok(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

    //Update Data Profil
    fun updateDataProfil(context:Context,tbl: String, col : String, vals : String, ){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateDataProfil(tbl,col,vals,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateProfile(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateProfile(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateProfile(response.body()?.pesan ?: "")
                    }

                }

            })
    }
    //Get Data User
    fun getDataUser(context:Context){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataUser(LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultDataUser>{
                override fun onFailure(call: Call<ResultDataUser>, t: Throwable) {
                    crudView.onErrorGetDataUser(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataUser>, response: Response<ResultDataUser>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultUserOne
                            crudView.onSuccessGetDataUser(data)
                        } else{
                            crudView.onErrorGetDataUser(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }


    //--------------------------Pengelolaan Inventory Kantor

    //fungsi get1
    fun getDataItemKantor(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataKantor(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultDataItemKantor>{
                override fun onFailure(call: Call<ResultDataItemKantor>, t: Throwable) {
                    crudView.onErrorGetdataItemKantor(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemKantor>, response: Response<ResultDataItemKantor>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemKantor
                            crudView.onSuccessGetDataItemKantor(data)
                        } else{
                            crudView.onErrorGetdataItemKantor(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi get2
    fun getDataItemKantorKeluar(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataKantorKeluar(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultDataItemKantorKeluar>{
                override fun onFailure(call: Call<ResultDataItemKantorKeluar>, t: Throwable) {
                    crudView.onErrorGetDataItemKantorKeluar(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemKantorKeluar>, response: Response<ResultDataItemKantorKeluar>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemKantorkeluar
                            crudView.onSuccessGetDataItemKantorKeluar(data)
                        } else{
                            crudView.onErrorGetDataItemKantorKeluar(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi get3
    fun getDataItemKantorMasuk(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataKantorMasuk(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultDataItemKantorMasuk>{
                override fun onFailure(call: Call<ResultDataItemKantorMasuk>, t: Throwable) {
                    crudView.onErrorGetDataItemKantorMasuk(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemKantorMasuk>, response: Response<ResultDataItemKantorMasuk>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemKantormasuk
                            crudView.onSuccessGetDataItemKantorMasuk(data)
                        } else{
                            crudView.onErrorGetDataItemKantorMasuk(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi get4
    fun getDataItemKantorGetItemById(context:Context,datasearch : String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getitembyidkantor(LoginUtama.globalDatabase,datasearch)
            .enqueue(object : retrofit2.Callback<ResultDataItemKantorGetItemById>{
                override fun onFailure(call: Call<ResultDataItemKantorGetItemById>, t: Throwable) {
                    crudView.onErrorGetDataItemKantorById(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemKantorGetItemById>, response: Response<ResultDataItemKantorGetItemById>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemKantorById
                            crudView.onSuccessGetDataItemKantorById(data)
                        } else{
                            crudView.onErrorGetDataItemKantorById(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi add 1
    fun addDataItemKantor(context:Context,itemno : String, deskripsi : String,kategoriitem : String, qty : Float,satuan : String, harga : String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addKantor(itemno,deskripsi, kategoriitem,qty,satuan,harga,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAddDataItemKantor(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAddDataItemKantor(response.body()?.pesan ?: "")
                    }else {
                        crudView.onErrorAddDataItemKantor(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi add 2
    fun addDataItemKantorKeluar(context:Context,itemno : String, tglcreate: String,qty : Float, catatan : String,lotnumber : String, minusplus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addKantorKeluar(itemno, tglcreate,qty,catatan,lotnumber,minusplus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAddDataItemKantorKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAddDataItemKantorKeluar(response.body()?.pesan ?: "")
                    }else {
                        crudView.onErrorAddDataItemKantorKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi add 3
    fun addDataItemKantorMasuk(context:Context,itemno : String, tglcreate: String,qty : Float, catatan : String,lotnumber : String, minusplus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addKantorMasuk(itemno, tglcreate,qty,catatan,lotnumber,minusplus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAddDataItemKantorMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAddDataItemKantorMasuk(response.body()?.pesan ?: "")
                    }else {
                        crudView.onErrorAddDataItemKantorMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi update 1
    fun updateDataItemKantor(context:Context, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateItemKantor(itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateDataItemKantor(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateDataItemKantor(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateDataItemKantor(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //fungsi update 2
    fun updateDataItemKantorKeluar(context:Context,idtransaksi: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateKantorKeluar(idtransaksi,itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateDataItemKantorKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateDataItemKantorKeluar(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateDataItemKantorKeluar(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //fungsi update 3
    fun updateDataItemKantorMasuk(context:Context,idtransaksi: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateKantorMasuk(idtransaksi,itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateDataItemKantorMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateDataItemKantorMasuk(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateDataItemKantorMasuk(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //fungsi delete 1
    fun hapusDataItemKantor(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteItemKantor(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteDataItemKantor(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteDataItemKantor(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteDataItemKantor(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi delete 2
    fun hapusDataItemKantorKeluar(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteKantorKeluar(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteDataItemKantorKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteDataItemKantorKeluar(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteDataItemKantorKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi delete 3
    fun hapusDataItemKantorMasuk(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteKantorMasuk(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteDataItemKantorMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteDataItemKantorMasuk(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteDataItemKantorMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //---------------------------Pengelolaan Inventory Sparepart

    //fungsi get1
    fun getDataItemSparepart(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataSparepart(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultDataItemSparepart>{
                override fun onFailure(call: Call<ResultDataItemSparepart>, t: Throwable) {
                    crudView.onErrorGetdataItemSparepart(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemSparepart>, response: Response<ResultDataItemSparepart>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemSparepart
                            crudView.onSuccessGetDataItemSparepart(data)
                        } else{
                            crudView.onErrorGetdataItemSparepart(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi get2
    fun getDataItemSparepartKeluar(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDatasparepartKeluar(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultDataItemSparepartKeluar>{
                override fun onFailure(call: Call<ResultDataItemSparepartKeluar>, t: Throwable) {
                    crudView.onErrorGetDataItemSparepartKeluar(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemSparepartKeluar>, response: Response<ResultDataItemSparepartKeluar>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemSparepartKeluar
                            crudView.onSuccessGetDataItemSparepartKeluar(data)
                        } else{
                            crudView.onErrorGetDataItemSparepartKeluar(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi get3
    fun getDataItemSparepartMasuk(context:Context,datasearch : String,limitstart :String,limitend:String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDatasparepartmasuk(LoginUtama.globalDatabase,datasearch,limitstart,limitend)
            .enqueue(object : retrofit2.Callback<ResultDataItemSparepartMasuk>{
                override fun onFailure(call: Call<ResultDataItemSparepartMasuk>, t: Throwable) {
                    crudView.onErrorGetDataItemSparepartMasuk(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemSparepartMasuk>, response: Response<ResultDataItemSparepartMasuk>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemSparepartMasuk
                            crudView.onSuccessGetDataItemSparepartMasuk(data)
                        } else{
                            crudView.onErrorGetDataItemSparepartMasuk(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi get4
    fun getDataItemSparepartGetItemById(context:Context,datasearch : String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getitembyidsparepart(LoginUtama.globalDatabase,datasearch)
            .enqueue(object : retrofit2.Callback<ResultDataItemSparepartGetItemById>{
                override fun onFailure(call: Call<ResultDataItemSparepartGetItemById>, t: Throwable) {
                    crudView.onErrorGetDataItemSparepartById(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultDataItemSparepartGetItemById>, response: Response<ResultDataItemSparepartGetItemById>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.ResultItemSparepartById
                            crudView.onSuccessGetDataItemSparepartById(data)
                        } else{
                            crudView.onErrorGetDataItemSparepartById(
                                response.body()?.pesan ?: "Error $status"
                            )
                        }
                    }
                }

            })
    }

    //fungsi add 1
    fun addDataItemSparepart(context:Context,itemno : String, deskripsi : String,kategoriitem : String, qty : Float,satuan : String, harga : String){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addSparepart(itemno,deskripsi, kategoriitem,qty,satuan,harga,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAddDataItemSparepart(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAddDataItemSparepart(response.body()?.pesan ?: "")
                    }else {
                        crudView.onErrorAddDataItemSparepart(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi add 2
    fun addDataItemSparepartKeluar(context:Context,itemno : String, tglcreate: String,qty : Float, catatan : String,lotnumber : String, minusplus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addSparepartKeluar(itemno, tglcreate,qty,catatan,lotnumber,minusplus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAddDataItemSparepartKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAddDataItemSparepartKeluar(response.body()?.pesan ?: "")
                    }else {
                        crudView.onErrorAddDataItemSparepartKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi add 3
    fun addDataItemSparepartMasuk(context:Context,itemno : String, tglcreate: String,qty : Float, catatan : String,lotnumber : String, minusplus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .addSparepartMasuk(itemno, tglcreate,qty,catatan,lotnumber,minusplus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAddDataItemSparepartMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAddDataItemSparepartMasuk(response.body()?.pesan ?: "")
                    }else {
                        crudView.onErrorAddDataItemSparepartMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi update 1
    fun updateDataItemSparepart(context:Context, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateItemSparepart(itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateDataItemSparepart(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateDataItemSparepart(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateDataItemSparepart(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //fungsi update 2
    fun updateDataItemSparepartKeluar(context:Context,idtransaksi: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateSparepartKeluar(idtransaksi,itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateDataItemSparepartKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateDataItemSparepartKeluar(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateDataItemSparepartKeluar(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //fungsi update 3
    fun updateDataItemSparepartMasuk(context:Context,idtransaksi: String, itemno : String, tglcreate : String, qty : Float, catatan: String, loadnumber : String, inputMinusPlus : Float){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .updateSparepartMasuk(idtransaksi,itemno,tglcreate,qty,catatan ,loadnumber,inputMinusPlus,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdateDataItemSparepartMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdateDataItemSparepartMasuk(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdateDataItemSparepartMasuk(response.body()?.pesan ?: "")
                    }

                }

            })
    }

    //fungsi delete 1
    fun hapusDataItemSparepart(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteItemSparepart(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteDataItemSparepart(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteDataItemSparepart(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteDataItemSparepart(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi delete 2
    fun hapusDataItemSparepartKeluar(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteSparepartKeluar(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteDataItemSparepartKeluar(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteDataItemSparepartKeluar(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteDataItemSparepartKeluar(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //fungsi delete 3
    fun hapusDataItemSparepartMasuk(context:Context,id: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java)
            .deleteSparepartMasuk(id,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDeleteDataItemSparepartMasuk(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDeleteDataItemSparepartMasuk(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDeleteDataItemSparepartMasuk(response.body()?.pesan ?: "")
                    }
                }

            })
    }

    //kategory
    fun getDataKategoryBySearch(context:Context,queryitem: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataKategoryLike(queryitem,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultGetItemById>{
                override fun onFailure(call: Call<ResultGetItemById>, t: Throwable) {
                    crudView.onErrorGetItemQuery(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultGetItemById>, response: Response<ResultGetItemById>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.GetItemByIds
                            crudView.onSuccessGetItemQuery(data)
                        } else{
                            crudView.onErrorGetItemQuery(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

    //Satuan
    fun getDataSatuanBySearch(context:Context,queryitem: String?){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataSatuanLike(queryitem,LoginUtama.globalDatabase)
            .enqueue(object : retrofit2.Callback<ResultGetItemById>{
                override fun onFailure(call: Call<ResultGetItemById>, t: Throwable) {
                    crudView.onErrorGetItemQuery(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultGetItemById>, response: Response<ResultGetItemById>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.GetItemByIds
                            crudView.onSuccessGetItemQuery(data)
                        } else{
                            crudView.onErrorGetItemQuery(response.body()?.pesan ?: "Error $status")
                        }
                    }
                }

            })
    }

}