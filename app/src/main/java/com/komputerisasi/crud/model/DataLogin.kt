package com.komputerisasi.crud.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataLogin : Serializable {

    @field:SerializedName("username")
    val username: String? = null

    @field:SerializedName("privilage")
    val privilage: String? = null

    @field:SerializedName("access_token")
    val accesstoken: String? = null

    @field:SerializedName("email")
    val email: String? = null
}