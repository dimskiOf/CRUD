package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultRmKeluarItem (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultRmKeluarItem")
    val RmKeluarItems: List<RmKeluarItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)