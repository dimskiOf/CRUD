package com.komputerisasi.crud.konfigurasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "database_settings.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }
            return instance as DatabaseHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Buat tabel pada database
        db?.createTable(
            SettingContract.TABLE_SETTING,
            true,
            SettingContract.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            SettingContract.NAME_SETTING to TEXT,
            SettingContract.VALUER to TEXT,
            SettingContract.STATUS to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(SettingContract.TABLE_SETTING, true)
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper(applicationContext)