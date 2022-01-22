package com.komputerisasi.crud.DataManagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.crud.R
import com.komputerisasi.crud.adapter.DataAdapter
import com.komputerisasi.crud.model.DataItem
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class FgKeluar : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg_keluar)
        presenter = Presenter(this)
        presenter.getData()

        btnTambah.setOnClickListener {
            startActivity<UpdateAddFgKeluar>()
            finish()
        }
    }


    override fun onSuccessGet(data: List<DataItem>?) {
        rvCategory.adapter = DataAdapter(data,object : DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                startActivity<UpdateAddFgKeluar>("dataItem" to item)
            }

            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.staffId)
                startActivity<FgKeluar>()
                finish()
            }

        })
    }

    override fun onFailedGet(msg: String) {
    }

    override fun onSuccessGetLogin(data: List<DataLogin>?) {
        val intent = intent
        val credential = intent.extras!!.getString("token")

        if (credential != null){
            btnSerialize.setText(credential.toString())
        }
    }

    override fun onFailedGetLogin(msg: String) {
    }

    override fun onSuccessDelete(msg: String) {
        presenter.getData()

    }

    override fun onErrorDelete(msg: String) {
        alert {
            title = "Error Delete Data"
        }.show()
    }

    override fun successAdd(msg: String) {
    }

    override fun errorAdd(msg: String) {
    }

    override fun onSuccessUpdate(msg: String) {
    }

    override fun onErrorUpdate(msg: String) {
    }
}