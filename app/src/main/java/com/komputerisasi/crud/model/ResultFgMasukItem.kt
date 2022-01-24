package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName

data class ResultFgMasukItem (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultFgMasukItem")
    val FgKeluarItems: List<FgMasukItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)
