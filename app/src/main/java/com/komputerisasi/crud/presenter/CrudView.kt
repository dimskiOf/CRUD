package com.komputerisasi.crud.presenter

import com.komputerisasi.crud.model.DataItem
import com.komputerisasi.crud.model.DataLogin

interface CrudView {
    //Untuk get data
    fun onSuccessGet(data: List<DataItem>?)
    fun onFailedGet(msg : String)

    //Untuk Login Data
    fun onSuccessGetLogin(data: List<DataLogin>?)
    fun onFailedGetLogin(msg : String)

    //Untuk Delete
    fun onSuccessDelete(msg: String)
    fun onErrorDelete(msg: String)

    //Untuk Add
    fun successAdd(msg : String)
    fun errorAdd(msg: String)

    //Untuk Update
    fun onSuccessUpdate(msg : String)
    fun onErrorUpdate(msg : String)

}