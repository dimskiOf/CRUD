package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemKantorKeluar (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemkantorkeluar")
    val ResultItemKantorkeluar: List<DataItemKantorKeluar>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)