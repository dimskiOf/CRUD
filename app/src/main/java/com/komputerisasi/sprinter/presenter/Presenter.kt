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
    fun getDataFgKeluar(context:Context){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataFgKeluar(LoginUtama.globalDatabase)
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
    fun getDataFgMasuk(context:Context){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataFgMasuk(LoginUtama.globalDatabase)
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
    fun getDataRmKeluar(context:Context){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataRmKeluar(LoginUtama.globalDatabase)
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
    fun getDataRmMasuk(context:Context){
        NetworkConfig.getService(context,LoginUtama.globalVar,DataService::class.java).getDataRmMasuk(LoginUtama.globalDatabase)
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


}