package com.komputerisasi.crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_login_utama.*
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.komputerisasi.crud.konfigurasi.SettingContract
import com.komputerisasi.crud.konfigurasi.database
import com.komputerisasi.crud.model.*
import kotlinx.android.synthetic.main.settings_layouts.*
import org.jetbrains.anko.db.insert
import android.content.ContentValues
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick


class LoginUtama() : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = Presenter(this)
        val data1 = selectDatabase("username")
        val data2 = selectDatabase("accesstoken")
        data1.forEach { i -> Toast.makeText(this, i.valuer, Toast.LENGTH_SHORT).show() }
        val cok2 = data2.forEach { i ->  }

        if (data1.isEmpty() || data2.isEmpty()) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_utama)

        settingserver.setOnClickListener {
            setContentView(R.layout.settings_layouts)


            btnSaveSetting.setOnClickListener {
                val stats = selectDatabase("settingurl")
                val urls = etSaveUrl.text.toString()
                if (stats.isNotEmpty()) {

                    //updateDatabase(urls,"settingurl",1)
                    insertDatabase(urls, 1, "settingurl")

                }else{
                    insertDatabase(urls, 1, "settingurl")
                }

            }
        }

        btnLogin.setOnClickListener {
            val email = username.text.toString()
            val password = password.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Insert Username and Password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                presenter.loginData(
                    email,
                    password
                )
            }
        }
    }else{
            super.onCreate(savedInstanceState)
           // setContentView(R.layout.activity_main)
            Toast.makeText(this, cok2.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    private fun insertDatabase(values: String,status: Int, namaseting: String) {
        database.use {
            insert(SettingContract.TABLE_SETTING,
                SettingContract.NAME_SETTING to namaseting,
                SettingContract.VALUER to values,
                SettingContract.STATUS to status)
        }

    }

     private fun updateDatabase(values: String,namaseting: String,status: Int){
         val cv = ContentValues()
         cv.put("valuer",values)
         cv.put("status",status)
         val selection = "${SettingContract.NAME_SETTING} = ?"
         val selectionArgs = arrayOf(namaseting)
         database.use { update(SettingContract.TABLE_SETTING,cv,selection,selectionArgs) }
     }

    private fun selectDatabases(namaseting: String):String {
        val db = database.readableDatabase
        val projection = arrayOf(SettingContract.NAME_SETTING, SettingContract.VALUER, SettingContract.STATUS)
        val selection = "${SettingContract.NAME_SETTING} = ?"
        val selectionArgs = arrayOf(namaseting)
        val sortOrder = "${SettingContract.NAME_SETTING} DESC"
        val c = db?.query(SettingContract.TABLE_SETTING,projection,selection,selectionArgs,null,null,sortOrder)
        val itemIds = mutableListOf<Long>()
        if (c != null) {
            with(c) {
                while (moveToNext()) {
                    val itemId = getLong(getColumnIndexOrThrow(SettingContract.VALUER))
                    itemIds.add(itemId)
                }
            }
        }
       return itemIds.toString()

        db.close()
    }

    private fun selectDatabase(namaseting: String): List<SettingContract> {
        var listData: List<SettingContract>? = null
        val projection = arrayOf(SettingContract.NAME_SETTING, SettingContract.VALUER, SettingContract.STATUS)
        val selection = "${SettingContract.NAME_SETTING} = ?"
        val selectionArgs = arrayOf(namaseting)
        val sortOrder = "${SettingContract.NAME_SETTING} DESC"
        database.use {
            val result = query(SettingContract.TABLE_SETTING,projection,selection,selectionArgs,null,null,sortOrder)
            listData = result.parseList(classParser<SettingContract>())
        }
        return listData!!
    }

    override fun onSuccessGetLogin(data: List<DataLogin>?) {
        data?.forEach{
            i ->
            val b = Intent(this, MainActivity::class.java)
            b.putExtra("token", "Selamat datang "+i.username)
            insertDatabase(i.username.toString(),1,"username")
            insertDatabase(i.accesstoken.toString(),1,"accesstoken")
            startActivity(b)
        }

    }

    override fun onFailedGetLogin(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun errorAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }
    override fun successAddFgMasuk(msg: String) {
    }

    override fun onSuccessDeleteFgMasuk(msg: String) {
    }

    override fun onSuccessGetFgMasuk(data: List<FgMasukItem>?) {
    }

    override fun onSuccessUpdateFgMasuk(msg: String) {
    }
    override fun errorAddFgMasuk(msg: String) {
    }

    override fun onErrorDeleteFgMasuk(msg: String) {
    }

    override fun onErrorUpdateFgMasuk(msg: String) {
    }

    override fun onFailedGetFgMasuk(msg: String) {
    }

    override fun onSuccessGetRmKeluar(data: List<RmKeluarItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetRmMasuk(data: List<RmMasukItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateRmKeluark(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

     override fun onSuccessgetToken(msg: String) {
         TODO("Not yet implemented")
     }

     override fun onFailedgetToken(msg: String) {
         Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
     }

}