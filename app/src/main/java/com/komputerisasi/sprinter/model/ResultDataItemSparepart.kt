package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataItemSparepart (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("itemsparepart")
    val ResultItemSparepart: List<DataItemSparepart>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)