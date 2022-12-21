package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class DataItemSparepartGetItemById : Serializable {

    @field:SerializedName("itemno")
    val ItemCode: String? = null

    @field:SerializedName("itemdescription")
    val Itemdes: String? = null

    @field:SerializedName("unit1")
    val Unit1: String? = null

    @field:SerializedName("unit2")
    val Unit2: String? = null

    @field:SerializedName("unit3")
    val Unit3: String? = null

    @field:SerializedName("minimumqty")
    val Minimumqty: String? = null

    @field:SerializedName("quantity")
    val Quantity: String? = null

}