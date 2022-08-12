package com.komputerisasi.sprinter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_login_utama.*
import kotlinx.android.synthetic.main.settings_layouts.*
import org.jetbrains.anko.startActivity
import java.io.IOException
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy



class LoginUtama() : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalVar = selectDatabase("settingurl")
        globalDatabase = selectDatabase("settingdatabase")
        globalCookie = selectDatabase("cookies")

        presenter = Presenter(this)

        try {
            presenter.Pingapi(applicationContext, "rezdnov123")
        }catch(e: IOException){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }

        // enable cookies
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        CookieHandler.setDefault(cookieManager)

        val data1 = selectDatabase("username")
        val data2 = selectDatabase("accesstoken")

        if (data1.isEmpty() || data2.isEmpty()) {
        setContentView(R.layout.activity_login_utama)

        settingserver.setOnClickListener {
            setContentView(R.layout.settings_layouts)

            val actionbar = supportActionBar
            //set actionbar title
            actionbar!!.title = "SERVER SETTING"
            //set back button
            actionbar.setDisplayHomeAsUpEnabled(true)

            val check = selectDatabase("settingurl")
            val check2 = selectDatabase("settingdatabase")
            etSaveUrl.setText(check)
            etSaveDatabase.setText(check2)

            btnSaveSetting.setOnClickListener {
                val urls = etSaveUrl.text.toString()
                deleteDatabase("table_settings", "settingurl","name_setting")
                insertDatabase(urls, 1, "settingurl")
                globalVar = selectDatabase("settingurl")
                globalDatabase = selectDatabase("settingdatabase")
                globalCookie = selectDatabase("cookies")
                try {
                    presenter.Pingapi(applicationContext, "rezdnov123")
                }catch(e: IOException){
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }

                val db = etSaveDatabase.text.toString()
                presenter.DbName(this,
                   db
                )


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
                    this,email,
                    password
                )
            }
        }
    }else{
            startActivity<MainActivity>()
            finish()
        }
    }
    override fun onSuccessGetDBname(msg: String) {
        val db = etSaveDatabase.text.toString()
        deleteDatabase("table_settings", "settingdatabase","name_setting")
        insertDatabase(db, 1, "settingdatabase")
        Toast.makeText(this, "Berhasil input server", Toast.LENGTH_SHORT).show()
        startActivity<LoginUtama>()
        finish()
    }

    override fun onErrorGetDBname(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {

        var globalVar = ""
        var globalDatabase = ""
        var globalCookie = ""
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<LoginUtama>()
        finish()
        return true
    }



    private fun insertDatabase(valuess: String,status: Int, namaseting: String) {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
         db.execSQL("INSERT INTO table_settings (valuer,status,name_setting) VALUES('"+ valuess +"','"+status+"','"+namaseting+"')")
        db.close()
    }
    private fun deleteDatabase(TABEL: String,VALUE : String, namaseting: String) {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        db.execSQL("DELETE FROM "+ TABEL +" WHERE "+ namaseting +" =  '"+ VALUE +"' ")
        db.close()
    }

     private fun updateDatabase(valuess: String,namaseting: String,status: Int){
         var helper = DatabaseHelper(applicationContext)
         var db = helper.readableDatabase
         db.execSQL("UPDATE table_settings set valuer='"+ valuess +"',status ='"+status+"' where name_setting= '"+namaseting+"'")
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

    override fun onSuccessGetLogin(data: List<DataLogin>?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(
            "finish",
            true
        )
        data?.forEach{
            i ->
            intent.putExtra("token", "Selamat datang "+i.username)
            insertDatabase(i.username.toString(),1,"username")
            insertDatabase(i.accesstoken.toString(),1,"accesstoken")
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
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

    override fun onSuccessGetItemById(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemById(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessPingApi(msg: String) {

    }

    override fun onErrorPingApi(msg: String) {

    }

}