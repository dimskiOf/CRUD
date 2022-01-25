package com.komputerisasi.crud.DataManagement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.crud.MainActivity
import com.komputerisasi.crud.R
import com.komputerisasi.crud.adapter.FgMasukAdapter
import com.komputerisasi.crud.model.*
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_fg_masuk.*
import org.jetbrains.anko.startActivity

class FgMasuk : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg_masuk)
        presenter = Presenter(this)
        presenter.getDataFgMasuk()

        btnKembali.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }

        btnTambah.setOnClickListener {
            startActivity<UpdateAddFgMasuk>()
            finish()
        }
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
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteFgMasuk(msg: String) {
        presenter.getDataFgMasuk()
    }

    override fun onSuccessGetFgMasuk(data: List<FgMasukItem>?) {
        rvCategory.adapter = FgMasukAdapter(data,object : FgMasukAdapter.onClickItem{
            override fun clicked(item: FgMasukItem?) {
                startActivity<UpdateAddFgMasuk>("dataItem" to item)
            }

            override fun delete(item: FgMasukItem?) {
                presenter.hapusDataFgMasuk(item?.IdFgMasuk)
                startActivity<FgMasuk>()
                finish()
            }
        })
    }

    override fun onSuccessUpdateFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }
    override fun errorAddFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorUpdateFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun errorAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetRmKeluar(data: List<RmKeluarItem>?) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateRmKeluark(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetRmMasuk(data: List<RmMasukItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteRmMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteRmMasuk(msg: String) {
        TODO("Not yet implemented")
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
}