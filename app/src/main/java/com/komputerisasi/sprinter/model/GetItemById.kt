package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class GetItemById : Serializable {

    @field:SerializedName("itemno")
    val ItemCode: String? = null

    @field:SerializedName("itemdescription")
    val Itemdes: String? = null

    @field:SerializedName("unit1")
    val Satuan: String? = null

    @field:SerializedName("unit3")
    val Unit3: String? = null

    @field:SerializedName("minimumqty")
    val Minimumqty: String? = null

    @field:SerializedName("quantity")
    val Quantity: String? = null

    @field:SerializedName("catatan")
    val Catatan: String? = null

    @field:SerializedName("SD")
    val MinusPlus: String? = "0"

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())

    @field:SerializedName("WQW")
    val TglMasuk: String = currentDate

}