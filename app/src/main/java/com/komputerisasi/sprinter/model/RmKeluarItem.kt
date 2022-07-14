package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RmKeluarItem : Serializable {

    @field:SerializedName("lot_nmbr")
    val LotNumber: String? = null

    @field:SerializedName("itemno")
    val ItemNo: String? = null

    @field:SerializedName("itemdescription")
    val ItemDescription: String? = null

    @field:SerializedName("unit1")
    val Unit1: String? = null

    @field:SerializedName("qty_rm")
    val QtyRm: String? = null

    @field:SerializedName("tgl_create_rm")
    val TglCreateRm: String? = null

    @field:SerializedName("inputminusplus")
    val InputMinusPlus: String? = null

    @field:SerializedName("id_rm_keluar")
    val IdRmKeluar: String? = null
}