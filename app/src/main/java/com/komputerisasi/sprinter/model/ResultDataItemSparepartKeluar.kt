package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemSparepartKeluar (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemsparepartkeluar")
    val ResultItemSparepartKeluar: List<DataItemSparepartKeluar>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)