package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FgMasukItem : Serializable {

    @field:SerializedName("lot_nmbr")
    val LotNumber: String? = null

    @field:SerializedName("itemno")
    val ItemNo: String? = null

    @field:SerializedName("itemdescription")
    val ItemDescription: String? = null

    @field:SerializedName("unit1")
    val Unit1: String? = null

    @field:SerializedName("qty_fg")
    val QtyFG: String? = null

    @field:SerializedName("tgl_create_fg")
    val TglCreateFg: String? = null

    @field:SerializedName("inputminusplus")
    val InputMinusPlus: String? = null

    @field:SerializedName("id_fg_masuk")
    val IdFgMasuk: String? = null
}