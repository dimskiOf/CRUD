package com.komputerisasi.crud.presenter

import com.komputerisasi.crud.model.*

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
    fun onSuccessGetRmMasuk(data: List<RmMasukItem>?)
    fun onFailedGetRmMasuk(msg : String)

    //Untuk Delete
    fun onSuccessDeleteFgKeluar(msg: String)
    fun onErrorDeleteFgKeluar(msg: String)
    fun onSuccessDeleteFgMasuk(msg: String)
    fun onErrorDeleteFgMasuk(msg: String)
    fun onSuccessDeleteRmKeluar(msg: String)
    fun onErrorDeleteRmKeluar(msg: String)
    fun onSuccessDeleteRmMasuk(msg: String)
    fun onErrorDeleteRmMasuk(msg: String)

    //Untuk Add
    fun successAddFgKeluar(msg: String)
    fun errorAddFgKeluar(msg: String)
    fun successAddFgMasuk(msg: String)
    fun errorAddFgMasuk(msg: String)
    fun successAddRmKeluar(msg: String)
    fun errorAddRmKeluar(msg: String)
    fun successAddRmMasuk(msg: String)
    fun errorAddRmMasuk(msg: String)

    //Untuk Update
    fun onSuccessUpdateFgKeluar(msg: String)
    fun onErrorUpdateFgKeluar(msg: String)
    fun onSuccessUpdateFgMasuk(msg: String)
    fun onErrorUpdateFgMasuk(msg: String)
    fun onSuccessUpdateRmKeluark(msg: String)
    fun onErrorUpdateRmKeluar(msg: String)
    fun onSuccessUpdateRmMasuk(msg: String)
    fun onErrorUpdateRmMasuk(msg: String)

}