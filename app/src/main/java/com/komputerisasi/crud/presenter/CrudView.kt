package com.komputerisasi.crud.presenter

import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.model.FgKeluarItem

interface CrudView {
    //Untuk get data
    fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?)
    fun onFailedGetFgKeluar(msg : String)

    //Untuk Login Data
    fun onSuccessGetLogin(data: List<DataLogin>?)
    fun onFailedGetLogin(msg : String)

    //Untuk Delete
    fun onSuccessDeleteFgKeluar(msg: String)
    fun onErrorDeleteFgKeluar(msg: String)

    //Untuk Add
    fun successAddFgKeluar(msg: String)
    fun errorAddFgKeluar(msg: String)

    //Untuk Update
    fun onSuccessUpdateFgKeluar(msg: String)
    fun onErrorUpdateFgKeluar(msg: String)

}