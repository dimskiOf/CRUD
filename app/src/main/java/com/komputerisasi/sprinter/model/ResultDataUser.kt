package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName

data class ResultDataUser (
    @field:SerializedName("pesan")
    val pesan: String? = null,


    @field:SerializedName("usercred")
    val ResultUserOne: List<DataUser>? = null,

    @field:SerializedName("status")
    val status: Int? = null
)