package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName

data class ResultRmMasukItem (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultRmMasukItem")
    val RmMasukItems: List<RmMasukItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)