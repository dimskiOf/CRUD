package com.komputerisasi.sprinter.presenter

import com.komputerisasi.sprinter.model.*

interface CrudView {
    //Untuk Login Data
    fun onSuccessGetLogin(data: List<DataLogin>?)
    fun onFailedGetLogin(msg : String)
    fun onSuccessgetToken(msg: String)
    fun onFailedgetToken(msg: String)

    //Untuk get data
    fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?)
    fun onFailedGetFgKeluar(msg : String)
    fun onSuccessGetFgMasuk(data: List<FgMasukItem>?)
    fun onFailedGetFgMasuk(msg : String)
    fun onSuccessGetRmKeluar(data: List<RmKeluarItem>?)
    fun onFailedGetRmKeluar(msg : String)
    fun onSuccessGetRmMasuk(data: List<RmMasukItem>?)
    fun onFailedGetRmMasuk(msg : String)
    fun onSuccessGetChekStok(data: List<ChekStokItem>?)
    fun onFailedGetChekStok(msg: String)
    fun onSuccessGetScanChekStok(data: List<ChekStokItem>?)
    fun onFailedGetScanChekStok(msg: String)

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

    //Get Item By Id
    fun onSuccessGetItemById(data: List<GetItemById>?)
    fun onErrorGetItemById(msg: String)

    //get item query
    fun onSuccessGetItemQuery(data: List<GetItemById>?)
    fun onErrorGetItemQuery(msg: String)

    //Ping Api
    fun onSuccessPingApi(msg: String)
    fun onErrorPingApi(msg: String)

    //DBNAME
    fun onSuccessGetDBname(msg: String)
    fun onErrorGetDBname(msg: String)

    //update profile
    fun onSuccessUpdateProfile(msg: String)
    fun onErrorUpdateProfile(msg: String)

    //Get data user
    fun onSuccessGetDataUser(data: List<DataUser>?)
    fun onErrorGetDataUser(msg: String)

    //-----------------------INVENTORY KANTOR-----------------\\

    fun onSuccessGetDataItemKantor(data: List<DataItemKantor>?)
    fun onErrorGetdataItemKantor(msg: String)

    fun onSuccessGetDataItemKantorKeluar(data: List<DataItemKantorKeluar>?)
    fun onErrorGetDataItemKantorKeluar(msg: String)

    fun onSuccessGetDataItemKantorMasuk(data: List<DataItemKantorMasuk>?)
    fun onErrorGetDataItemKantorMasuk(msg: String)

    fun onSuccessGetDataItemKantorById(data: List<DataItemKantorGetItemById>?)
    fun onErrorGetDataItemKantorById(msg: String)

    fun onSuccessAddDataItemKantor(msg: String)
    fun onErrorAddDataItemKantor(msg: String)

    fun onSuccessAddDataItemKantorKeluar(msg: String)
    fun onErrorAddDataItemKantorKeluar(msg: String)

    fun onSuccessAddDataItemKantorMasuk(msg: String)
    fun onErrorAddDataItemKantorMasuk(msg: String)

    fun onSuccessUpdateDataItemKantor(msg: String)
    fun onErrorUpdateDataItemKantor(msg: String)

    fun onSuccessUpdateDataItemKantorKeluar(msg: String)
    fun onErrorUpdateDataItemKantorKeluar(msg: String)

    fun onSuccessUpdateDataItemKantorMasuk(msg: String)
    fun onErrorUpdateDataItemKantorMasuk(msg: String)

    fun onSuccessDeleteDataItemKantor(msg: String)
    fun onErrorDeleteDataItemKantor(msg: String)

    fun onSuccessDeleteDataItemKantorKeluar(msg: String)
    fun onErrorDeleteDataItemKantorKeluar(msg: String)

    fun onSuccessDeleteDataItemKantorMasuk(msg: String)
    fun onErrorDeleteDataItemKantorMasuk(msg: String)

    //-----------------------END INVENTORY KANTOR-----------------\\

    //-----------------------INVENTORY SPAREPART-----------------\\

    fun onSuccessGetDataItemSparepart(data: List<DataItemSparepart>?)
    fun onErrorGetdataItemSparepart(msg: String)

    fun onSuccessGetDataItemSparepartKeluar(data: List<DataItemSparepartKeluar>?)
    fun onErrorGetDataItemSparepartKeluar(msg: String)

    fun onSuccessGetDataItemSparepartMasuk(data: List<DataItemSparepartMasuk>?)
    fun onErrorGetDataItemSparepartMasuk(msg: String)

    fun onSuccessGetDataItemSparepartById(data: List<DataItemSparepartGetItemById>?)
    fun onErrorGetDataItemSparepartById(msg: String)

    fun onSuccessAddDataItemSparepart(msg: String)
    fun onErrorAddDataItemSparepart(msg: String)

    fun onSuccessAddDataItemSparepartKeluar(msg: String)
    fun onErrorAddDataItemSparepartKeluar(msg: String)

    fun onSuccessAddDataItemSparepartMasuk(msg: String)
    fun onErrorAddDataItemSparepartMasuk(msg: String)

    fun onSuccessUpdateDataItemSparepart(msg: String)
    fun onErrorUpdateDataItemSparepart(msg: String)

    fun onSuccessUpdateDataItemSparepartKeluar(msg: String)
    fun onErrorUpdateDataItemSparepartKeluar(msg: String)

    fun onSuccessUpdateDataItemSparepartMasuk(msg: String)
    fun onErrorUpdateDataItemSparepartMasuk(msg: String)

    fun onSuccessDeleteDataItemSparepart(msg: String)
    fun onErrorDeleteDataItemSparepart(msg: String)

    fun onSuccessDeleteDataItemSparepartKeluar(msg: String)
    fun onErrorDeleteDataItemSparepartKeluar(msg: String)

    fun onSuccessDeleteDataItemSparepartMasuk(msg: String)
    fun onErrorDeleteDataItemSparepartMasuk(msg: String)

    //-----------------------END INVENTORY SPAREPART-----------------\\

    //---------------------kategori item------------------------------\\

    fun onSuccessGetKategoryItemLike(msg: String)
    fun onErrorGetKategoryItemLike(msg: String)

    //--------------------end kategori-------------------------------\\

    //---------------------satuan item------------------------------\\

    fun onSuccessGetSatuanItemLike(msg: String)
    fun onErrorGetSatuanItemLike(msg: String)

    //--------------------end satuan-------------------------------\\
}