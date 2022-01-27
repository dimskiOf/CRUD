package com.komputerisasi.crud.DataManagement

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.crud.LoginUtama
import com.komputerisasi.crud.MainActivity
import com.komputerisasi.crud.R
import com.komputerisasi.crud.Scanning.ScannRmMasuk
import com.komputerisasi.crud.adapter.RmMasukAdapter
import com.komputerisasi.crud.konfigurasi.DatabaseHelper
import com.komputerisasi.crud.model.*
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_rm_masuk.*
import org.jetbrains.anko.startActivity

class RmMasuk : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        val data1 = selectDatabase("username")
        val data2 = selectDatabase("accesstoken")

        if (data1.isEmpty() || data2.isEmpty()){
            startActivity<LoginUtama>()
            finish()
        }else{

        }

        LoginUtama.globalVar = selectDatabase("settingurl")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rm_masuk)
        presenter = Presenter(this)
        presenter.getDataRmMasuk()

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "RM MASUK"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        btnTambah.setOnClickListener {
            startActivity<ScannRmMasuk>()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<MainActivity>()
        finish()
        return true
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

    override fun onSuccessGetLogin(data: List<DataLogin>?) {}

    override fun onFailedGetLogin(msg: String) {
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
    }

    override fun onFailedGetFgKeluar(msg: String) {

    }

    override fun onSuccessDeleteFgKeluar(msg: String) {

    }

    override fun onErrorDeleteFgKeluar(msg: String) {

    }

    override fun successAddFgKeluar(msg: String) {
    }

    override fun errorAddFgKeluar(msg: String) {
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
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

    override fun errorAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteRmKeluar(msg: String) {

    }

    override fun onErrorUpdateRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetRmKeluar(msg: String) {

    }

    override fun onSuccessDeleteRmKeluar(msg: String) {

    }

    override fun onSuccessGetRmKeluar(data: List<RmKeluarItem>?) {
    }

    override fun onSuccessUpdateRmKeluark(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetRmMasuk(data: List<RmMasukItem>?) {
        rvCategory.adapter = RmMasukAdapter(data,object :RmMasukAdapter.onClickItem{
            override fun clicked(item: RmMasukItem?) {
                val builder = AlertDialog.Builder(this@RmMasuk)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        startActivity<UpdateAddRmMasuk>("dataItem" to item)
                        finish()
                    }
                val alert = builder.create()
                alert.show()

            }

            override fun delete(item: RmMasukItem?) {
                val builder = AlertDialog.Builder(this@RmMasuk)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        presenter.hapusDataRmMasuk(item?.IdRmMasuk)
                        startActivity<RmMasuk>()
                        finish()
                    }
                val alert = builder.create()
                alert.show()

            }
        })
    }

    override fun onFailedGetRmMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteRmMasuk(msg: String) {
        presenter.getDataRmMasuk()
    }

    override fun onErrorDeleteRmMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun successAddRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddRmMasuk(msg: String) {
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
        TODO("Not yet implemented")
    }
    override fun onSuccessGetItemById(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemById(msg: String) {
        TODO("Not yet implemented")
    }
}