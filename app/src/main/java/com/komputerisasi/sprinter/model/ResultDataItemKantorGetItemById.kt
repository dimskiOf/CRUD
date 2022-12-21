package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemKantorGetItemById (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemkantorbyid")
    val ResultItemKantorById: List<DataItemKantorGetItemById>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)