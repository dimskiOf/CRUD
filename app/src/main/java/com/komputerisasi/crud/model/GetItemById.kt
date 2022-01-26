package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class GetItemById : Serializable {

    @field:SerializedName("ITEMNO")
    val ItemCode: String? = null

    @field:SerializedName("ITEMDESCRIPTION")
    val Itemdes: String? = null

    @field:SerializedName("")
    val Satuan: String = "Sack"

    @field:SerializedName("")
    val MinusPlus: String = "0"

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())

    @field:SerializedName("")
    val TglMasuk: String = currentDate

}