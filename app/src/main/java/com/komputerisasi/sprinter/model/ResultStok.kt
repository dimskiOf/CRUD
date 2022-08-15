package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultStok (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultChekStokItem")
    val ResultStok: List<ChekStokItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)