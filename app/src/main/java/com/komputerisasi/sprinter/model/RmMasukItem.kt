package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RmMasukItem : Serializable {

    @field:SerializedName("lot_nmbr")
    val LotNumber: String? = null

    @field:SerializedName("itemno")
    val ItemNo: String? = null

    @field:SerializedName("itemdescription")
    val ItemDescription: String? = null

    @field:SerializedName("unit1")
    val Unit1: String? = null

    @field:SerializedName("unit3")
    val Unit3: String? = null

    @field:SerializedName("qty")
    val Qty: String? = null

    @field:SerializedName("catatan")
    val Catatan: String? = null

    @field:SerializedName("tgl_catatan")
    val TglCatatan: String? = null

    @field:SerializedName("inputminusplus")
    val InputMinusPlus: String? = null

    @field:SerializedName("id_warehouse_InOut")
    val Id_warehouse_InOut: String? = null
}