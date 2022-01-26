package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName

data class ResultGetItemById (
    @field:SerializedName("pesan")
    val pesan: String? = null,

    @field:SerializedName("ResultGetItem")
    val GetItemByIds: List<GetItemById>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)