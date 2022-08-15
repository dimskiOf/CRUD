package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultCheckStok (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultChekStokItem")
    val ChekStokItem: List<ChekStokItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)
