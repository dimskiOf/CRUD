package com.komputerisasi.sprinter

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.sprinter.DataManagement.FgKeluar
import com.komputerisasi.sprinter.DataManagement.FgMasuk
import com.komputerisasi.sprinter.DataManagement.RmKeluar
import com.komputerisasi.sprinter.DataManagement.RmMasuk
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import android.content.Intent
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import java.io.IOException


class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)

        LoginUtama.globalVar

        try {
            presenter.Pingapi(applicationContext, "rezdnov123")
        }catch(e: IOException){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }

        FgKeluar.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<FgKeluar>()
            finish()
        }
        FgMasuk.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<FgMasuk>()
            finish()
        }
        RmKeluar.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<RmKeluar>()
            finish()
        }
        RmMasuk.setOnClickListener {
            try {
                presenter.Pingapi(applicationContext, "rezdnov123")
            }catch(e: IOException){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
            startActivity<RmMasuk>()
            finish()
        }

        Logout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // Delete selected note from database
                    deletesDatabase("username")
                    deletesDatabase("accesstoken")
                    val intent = Intent(this, LoginUtama::class.java)
                    intent.putExtra(
                        "finish",
                        true
                    )

                    try {
                        presenter.Pingapi(applicationContext, "logout")
                    }catch(e: IOException){
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                    }

                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()

                    Toast.makeText(this, "Logout!!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

    }
    private fun deletesDatabase(namaseting: String) {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        db.execSQL("delete from table_settings where name_setting = '"+namaseting+"'")
        db.close()
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
}