package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName

data class ResultFgKeluarItem (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultFgKeluarItem")
    val FgKeluarItems: List<FgKeluarItem>? = null,

    @field:SerializedName("status")
    val status: Int? = null

)