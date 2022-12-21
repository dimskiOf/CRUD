package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class DataItemKantorKeluar : Serializable {

    @field:SerializedName("id_warehouse_InOut")
    val Id_transaksi: String? = null

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

    @field:SerializedName("catatan")
    val Catatan: String? = null

    @field:SerializedName("tglcatatan")
    val TglCatatan: String? = null

    @field:SerializedName("inputminusplus")
    val Minusplus: String? = null

    @field:SerializedName("lot_nmbr")
    val Lot_Number: String? = null

    @field:SerializedName("minimumqty")
    val Minimumqty: String? = null

    @field:SerializedName("quantity")
    val Quantity: String? = null

}