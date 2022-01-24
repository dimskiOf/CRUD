package com.komputerisasi.crud.DataManagement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.crud.R
import com.komputerisasi.crud.adapter.FgKeluarAdapter
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.model.FgKeluarItem
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_fg_keluar.*
import org.jetbrains.anko.startActivity

class FgKeluar : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg_keluar)
        presenter = Presenter(this)
        presenter.getDataFgKeluar()

        btnTambah.setOnClickListener {
            startActivity<UpdateAddFgKeluar>()
            finish()
        }
    }

    override fun onSuccessGetLogin(data: List<DataLogin>?) {}

    override fun onFailedGetLogin(msg: String) {
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        rvCategory.adapter = FgKeluarAdapter(data,object :FgKeluarAdapter.onClickItem{
            override fun clicked(item: FgKeluarItem?) {
                startActivity<UpdateAddFgKeluar>("dataItem" to item)
            }

            override fun delete(item: FgKeluarItem?) {
                presenter.hapusDataFgKeluar(item?.IdFgKeluar)
                startActivity<FgKeluar>()
                finish()
            }
        })
    }

    override fun onFailedGetFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteFgKeluar(msg: String) {
        presenter.getDataFgKeluar()
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun successAddFgKeluar(msg: String) {
    }

    override fun errorAddFgKeluar(msg: String) {
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
    }

}