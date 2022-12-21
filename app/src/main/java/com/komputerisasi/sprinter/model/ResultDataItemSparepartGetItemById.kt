package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemSparepartGetItemById (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemsparepartbyid")
    val ResultItemSparepartById: List<DataItemSparepartGetItemById>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)