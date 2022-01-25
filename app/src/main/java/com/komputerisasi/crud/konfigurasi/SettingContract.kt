package com.komputerisasi.crud.konfigurasi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SettingContract(
    val id: Long?,
    val name_setting: String,
    val valuer: String,
    val status: Int
) : Parcelable {
    companion object{
        const val TABLE_SETTING = "table_settings"
        const val ID = "id"
        const val NAME_SETTING = "name_setting"
        const val VALUER = "valuer"
        const val STATUS = "status"
    }
}