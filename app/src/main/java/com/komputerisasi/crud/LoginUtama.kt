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
import com.komputerisasi.crud.model.*
import kotlinx.android.synthetic.main.settings_layouts.*
import com.komputerisasi.crud.konfigurasi.DatabaseHelper
import org.jetbrains.anko.startActivity


class LoginUtama() : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalVar = selectDatabase("settingurl")

        presenter = Presenter(this)
        val data1 = selectDatabase("username")
        val data2 = selectDatabase("accesstoken")

        if (data1.isEmpty() || data2.isEmpty()) {
        setContentView(R.layout.activity_login_utama)

        settingserver.setOnClickListener {
            setContentView(R.layout.settings_layouts)

            val actionbar = supportActionBar
            //set actionbar title
            actionbar!!.title = "lOGIN"
            //set back button
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)

            btnSaveSetting.setOnClickListener {
                val stats = selectDatabase("settingurl")
                val urls = etSaveUrl.text.toString()
                if (stats.isNotEmpty()) {
                    updateDatabase(urls,"settingurl",1)
                    Toast.makeText(this, "Berhasil update server", Toast.LENGTH_SHORT).show()
                    startActivity<LoginUtama>()
                    finish()
                }else{
                    insertDatabase(urls, 1, "settingurl")
                    Toast.makeText(this, "Berhasil input server", Toast.LENGTH_SHORT).show()
                    startActivity<LoginUtama>()
                    finish()
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
            startActivity<MainActivity>()
            finish()
        }
    }

    companion object {

        var globalVar = ""
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

}