package com.komputerisasi.crud.DataManagement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.crud.R
import com.komputerisasi.crud.adapter.RmKeluarAdapter
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.model.FgKeluarItem
import com.komputerisasi.crud.model.FgMasukItem
import com.komputerisasi.crud.model.RmKeluarItem
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_fg_keluar.*
import org.jetbrains.anko.startActivity

class RmKeluar : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rm_keluar)
        presenter = Presenter(this)
        presenter.getDataRmKeluar()

        btnTambah.setOnClickListener {
            startActivity<UpdateAddRmKeluar>()
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorUpdateRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetRmKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteRmKeluar(msg: String) {
        presenter.getDataRmKeluar()
    }

    override fun onSuccessGetRmKeluar(data: List<RmKeluarItem>?) {
        rvCategory.adapter = RmKeluarAdapter(data,object :RmKeluarAdapter.onClickItem{
            override fun clicked(item: RmKeluarItem?) {
                startActivity<UpdateAddRmKeluar>("dataItem" to item)
            }

            override fun delete(item: RmKeluarItem?) {
                presenter.hapusDataRmKeluar(item?.IdRmKeluar)
                startActivity<RmKeluar>()
                finish()
            }
        })
    }

    override fun onSuccessUpdateRmKeluark(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddRmKeluar(msg: String) {
        TODO("Not yet implemented")
    }

}