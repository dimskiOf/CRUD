package com.komputerisasi.crud.presenter

import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.model.FgKeluarItem
import com.komputerisasi.crud.model.FgMasukItem
import com.komputerisasi.crud.model.RmKeluarItem

interface CrudView {
    //Untuk Login Data
    fun onSuccessGetLogin(data: List<DataLogin>?)
    fun onFailedGetLogin(msg : String)

    //Untuk get data
    fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?)
    fun onFailedGetFgKeluar(msg : String)
    fun onSuccessGetFgMasuk(data: List<FgMasukItem>?)
    fun onFailedGetFgMasuk(msg : String)
    fun onSuccessGetRmKeluar(data: List<RmKeluarItem>?)
    fun onFailedGetRmKeluar(msg : String)

    //Untuk Delete
    fun onSuccessDeleteFgKeluar(msg: String)
    fun onErrorDeleteFgKeluar(msg: String)
    fun onSuccessDeleteFgMasuk(msg: String)
    fun onErrorDeleteFgMasuk(msg: String)
    fun onSuccessDeleteRmKeluar(msg: String)
    fun onErrorDeleteRmKeluar(msg: String)

    //Untuk Add
    fun successAddFgKeluar(msg: String)
    fun errorAddFgKeluar(msg: String)
    fun successAddFgMasuk(msg: String)
    fun errorAddFgMasuk(msg: String)
    fun successAddRmKeluar(msg: String)
    fun errorAddRmKeluar(msg: String)

    //Untuk Update
    fun onSuccessUpdateFgKeluar(msg: String)
    fun onErrorUpdateFgKeluar(msg: String)
    fun onSuccessUpdateFgMasuk(msg: String)
    fun onErrorUpdateFgMasuk(msg: String)
    fun onSuccessUpdateRmKeluark(msg: String)
    fun onErrorUpdateRmKeluar(msg: String)

}