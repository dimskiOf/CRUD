package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemSparepartMasuk (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemsparepartmasuk")
    val ResultItemSparepartMasuk: List<DataItemSparepartMasuk>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)