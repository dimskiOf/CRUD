package com.komputerisasi.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komputerisasi.crud.DataManagement.FgKeluar
import com.komputerisasi.crud.DataManagement.FgMasuk
import com.komputerisasi.crud.DataManagement.RmKeluar
import com.komputerisasi.crud.DataManagement.RmMasuk
import com.komputerisasi.crud.konfigurasi.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data1 = selectDatabase("username")
        LoginUtama.globalVar = selectDatabase("settingurl")

        selamatdatang.setText(data1)

        FgKeluar.setOnClickListener {
            startActivity<FgKeluar>()
            finish()
        }
        FgMasuk.setOnClickListener {
            startActivity<FgMasuk>()
            finish()
        }
        RmKeluar.setOnClickListener {
            startActivity<RmKeluar>()
            finish()
        }
        RmMasuk.setOnClickListener {
            startActivity<RmMasuk>()
            finish()
        }

        Logout.setOnClickListener {
            deletesDatabase("username")
            deletesDatabase("accesstoken")
            startActivity<LoginUtama>()
            finish()
        }

    }
    private fun deletesDatabase(namaseting: String) {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        db.execSQL("delete from table_settings where name_setting = '"+namaseting+"'")
        db.close()
    }

    private fun selectDatabase(namaseting: String): String {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        var dt = db.rawQuery("select * from table_settings where name_setting = '"+namaseting+"'",null)
        if(dt.moveToNext()){
            return dt.getString(2)
        }
        return ""
        db.close()
    }
}