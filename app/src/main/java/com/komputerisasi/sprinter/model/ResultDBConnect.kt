package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResultDBConnect : Serializable {
    @field:SerializedName("pesan")
    val pesan: String? = null

    @field:SerializedName("status")
    val status: Int? = null
}