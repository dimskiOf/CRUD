package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName

data class ResultLogin (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("logincred")
    val logins: List<DataLogin>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)