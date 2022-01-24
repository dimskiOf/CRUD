package com.komputerisasi.crud.presenter

import android.util.Log
import com.komputerisasi.crud.model.ResultFgKeluarItem
import com.komputerisasi.crud.model.ResultLogin
import com.komputerisasi.crud.model.ResultStatus
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

}