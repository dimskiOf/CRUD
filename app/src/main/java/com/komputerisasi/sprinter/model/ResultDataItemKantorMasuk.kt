package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemKantorMasuk (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemkantormasuk")
    val ResultItemKantormasuk: List<DataItemKantorMasuk>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)