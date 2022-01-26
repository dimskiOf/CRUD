package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataLogin : Serializable {

    @field:SerializedName("USERNAME")
    val username: String? = null

    @field:SerializedName("ID_JABATAN")
    val privilage: String? = null

    @field:SerializedName("PASSWD")
    val accesstoken: String? = null

    @field:SerializedName("EMAIL")
    val email: String? = null
}