package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RmMasukItem : Serializable {

    @field:SerializedName("LOAD_NMBR")
    val LoadNumber: String? = null

    @field:SerializedName("ITEMNO")
    val ItemNo: String? = null

    @field:SerializedName("ITEMDESCRIPTION")
    val ItemDescription: String? = null

    @field:SerializedName("UNIT1")
    val Unit1: String? = null

    @field:SerializedName("QTY_RM")
    val QtyRm: String? = null

    @field:SerializedName("TGL_CREATE_RM")
    val TglCreateRm: String? = null

    @field:SerializedName("INPUTMINUSPLUS")
    val InputMinusPlus: String? = null

    @field:SerializedName("ID_RM_MASUK")
    val IdRmMasuk: String? = null
}