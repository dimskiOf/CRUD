package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChekStokItem : Serializable {

    @field:SerializedName("itemno")
    val ItemNo: String? = null

    @field:SerializedName("itemdescription")
    val ItemDescription: String? = null

    @field:SerializedName("unit1")
    val Unit1: String? = null

    @field:SerializedName("unit2")
    val Unit2: String? = null

    @field:SerializedName("unit3")
    val Unit3: String? = null

    @field:SerializedName("quantity")
    val Qty: String? = null

    @field:SerializedName("minimumqty")
    val Minimumqty: String? = null

    @field:SerializedName("warehouseid")
    val Warehousid: String? = null
}