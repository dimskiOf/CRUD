package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FgKeluarItem : Serializable {

    @field:SerializedName("LOAD_NMBR")
    val LoadNumber: String? = null

    @field:SerializedName("ITEMNO")
    val ItemNo: String? = null

    @field:SerializedName("ITEMDESCRIPTION")
    val ItemDescription: String? = null

    @field:SerializedName("UNIT1")
    val Unit1: String? = null

    @field:SerializedName("QTY_FG")
    val QtyFG: String? = null

    @field:SerializedName("TGL_CREATE_FG")
    val TglCreateFg: String? = null

    @field:SerializedName("INPUTMINUSPLUS")
    val InputMinusPlus: String? = null

    @field:SerializedName("ID_FG_KELUAR")
    val IdFgKeluar: String? = null
}