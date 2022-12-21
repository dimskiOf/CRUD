package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemKantor (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemkantor")
    val ResultItemKantor: List<DataItemKantor>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)