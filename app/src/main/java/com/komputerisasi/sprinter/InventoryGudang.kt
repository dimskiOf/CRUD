package com.komputerisasi.sprinter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import kotlinx.android.synthetic.main.activity_inventory_gudang.*
import org.jetbrains.anko.startActivity
import android.content.Intent
import com.komputerisasi.sprinter.DataManagement.*
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import java.io.IOException


class InventoryGudang : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_gudang)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "INVENTORI GUDANG"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        presenter = Presenter(this)

        LoginUtama.globalVar

        val loaduser = selectDatabase("datauser")

        if(loaduser.isNotEmpty()){

        }else{
            startActivity<LoginUtama>()
            finish()
        }

        try {
            presenter.Pingapi(applicationContext, "rezdnov123")
        }catch(e: IOException){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }

        checkstokfg.setOnClickListener{
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<CheckStokFG>()
            finish()
        }

        checkstokrm.setOnClickListener{
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<CheckStok>()
            finish()
        }

        barangjadikeluar.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<FgKeluar>()
            finish()
        }
        barangjadimasuk.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<FgMasuk>()
            finish()
        }
        material_keluar.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<RmKeluar>()
            finish()
        }
        materialmasuk.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<RmMasuk>()
            finish()
        }

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
    private fun deletesDatabase(namaseting: String) {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        db.execSQL("delete from table_settings where name_setting = '"+namaseting+"'")
        db.close()
    }
    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<MainActivity>()
        finish()
        return true
    }

    override fun onSuccessGetItemQuery(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemQuery(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetLogin(data: List<DataLogin>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetLogin(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessgetToken(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedgetToken(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgMasuk(data: List<FgMasukItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgMasuk(msg: String) {
        TODO("Not yet implemented")
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

    override fun onSuccessDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgMasuk(msg: String) {
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

    override fun successAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddFgMasuk(msg: String) {
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

    override fun onSuccessUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateFgMasuk(msg: String) {
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

    override fun onSuccessGetItemById(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemById(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessPingApi(msg: String) {

    }

    override fun onErrorPingApi(msg: String) {
        if(msg == "Logout") {
            deletesDatabase("username")
            deletesDatabase("accesstoken")
            val intent = Intent(this, LoginUtama::class.java)
            intent.putExtra(
                "finish",
                true
            )

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()

            Toast.makeText(this, "Session Habis!!", Toast.LENGTH_SHORT).show()
        }else if(msg == "Wrong Data"){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
        TODO("Not yet implemented")
    }
    override fun onSuccessGetChekStok(data: List<ChekStokItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetChekStok(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetScanChekStok(data: List<ChekStokItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetScanChekStok(msg: String) {
        TODO("Not yet implemented")
    }
    override fun onSuccessUpdateProfile(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateProfile(msg: String) {
        TODO("Not yet implemented")
    }
    override fun onSuccessGetDataUser(data: List<DataUser>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataUser(msg: String) {
        TODO("Not yet implemented")
    }
}