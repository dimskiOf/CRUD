package com.komputerisasi.sprinter.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataUser : Serializable {

    @field:SerializedName("username")
    val username: String? = null

    @field:SerializedName("privilage")
    val privilage: String? = null

    @field:SerializedName("no_hp")
    val nohp: String? = null

    @field:SerializedName("email")
    val email: String? = null

    @field:SerializedName("nama")
    val nama: String? = null
}