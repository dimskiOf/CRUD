package com.komputerisasi.crud.presenter

import android.util.Log
import com.komputerisasi.crud.model.*
import com.komputerisasi.crud.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter (val crudView: CrudView) {


    //get data login
    fun loginData(username : String, password : String){
        NetworkConfig.getService()
            .loginData(username, password)
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
    fun getDataFgKeluar(){
        NetworkConfig.getService().getDataFgKeluar()
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
                            crudView.onFailedGetFgKeluar("Error $status")
                        }
                    }
                }

            })
    }


    //Add data FG keluar
    fun addDataFgKeluar(itemno : String, tglcreate : String, qty : Int, loadnumber : String, inputMinusPlus : String){
        NetworkConfig.getService()
            .addFgKeluar(itemno, tglcreate, qty,loadnumber,inputMinusPlus)
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
    fun updateDataFgKeluar(idfgkeluar: String, itemno : String, tglcreate : String, qty : Int, loadnumber : String, inputMinusPlus : String){
        NetworkConfig.getService()
            .updateFgKeluar(idfgkeluar,itemno,tglcreate,qty,loadnumber, inputMinusPlus)
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
    fun hapusDataFgKeluar(id: String?){
        NetworkConfig.getService()
            .deleteFgKeluar(id)
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
    fun getDataFgMasuk(){
        NetworkConfig.getService().getDataFgMasuk()
            .enqueue(object : retrofit2.Callback<ResultFgMasukItem>{
                override fun onFailure(call: Call<ResultFgMasukItem>, t: Throwable) {
                    crudView.onFailedGetFgMasuk(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultFgMasukItem>, response: Response<ResultFgMasukItem>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.FgKeluarItems
                            crudView.onSuccessGetFgMasuk(data)
                        } else{
                            crudView.onFailedGetFgMasuk("Error $status")
                        }
                    }
                }

            })
    }


    //Add data FG Masuk
    fun addDataFgMasuk(itemno : String, tglcreate : String, qty : Int, loadnumber : String, inputMinusPlus : String){
        NetworkConfig.getService()
            .addFgMasuk(itemno, tglcreate, qty,loadnumber,inputMinusPlus)
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
    fun updateDataFgMasuk(idfgmasuk: String, itemno : String, tglcreate : String, qty : Int, loadnumber : String, inputMinusPlus : String){
        NetworkConfig.getService()
            .updateFgMasuk(idfgmasuk,itemno,tglcreate,qty,loadnumber, inputMinusPlus)
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
    fun hapusDataFgMasuk(id: String?){
        NetworkConfig.getService()
            .deleteFgMasuk(id)
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
    fun getDataRmKeluar(){
        NetworkConfig.getService().getDataRmKeluar()
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
                            crudView.onFailedGetRmKeluar("Error $status")
                        }
                    }
                }

            })
    }


    //Add data RM keluar
    fun addDataRmKeluar(itemno : String, tglcreate : String, qty : Int, loadnumber : String, inputMinusPlus : String){
        NetworkConfig.getService()
            .addRmKeluar(itemno, tglcreate, qty,loadnumber,inputMinusPlus)
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
    fun updateDataRmKeluar(idrmkeluar: String, itemno : String, tglcreate : String, qty : Int, loadnumber : String, inputMinusPlus : String){
        NetworkConfig.getService()
            .updateRmKeluar(idrmkeluar,itemno,tglcreate,qty,loadnumber, inputMinusPlus)
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
    fun hapusDataRmKeluar(id: String?){
        NetworkConfig.getService()
            .deleteRmKeluar(id)
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

}