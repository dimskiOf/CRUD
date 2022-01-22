package com.komputerisasi.crud.DataManagement

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komputerisasi.crud.MainActivity
import com.komputerisasi.crud.R
import com.komputerisasi.crud.model.DataItem
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_update_add.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class UpdateAddFgKeluar : AppCompatActivity(), CrudView {


    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add_fg_keluar)

        presenter = Presenter(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        if (itemDataItem == null){
            btnAction.text = "Tambah"
            btnAction.onClick {
                presenter.addData(
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAlamat.text.toString())
            }

        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.staffName.toString())
            etPhone.setText(item?.staffHp.toString())
            etAlamat.setText(item?.staffAlamat.toString())
            btnAction.onClick {
                presenter.updateData(
                    item?.staffId ?: "",
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAlamat.text.toString())
                finish()
            }

        }
    }



    override fun successAdd(msg: String) {
        startActivity<MainActivity>()
        finish()
    }

    override fun errorAdd(msg: String) {}

    override fun onSuccessUpdate(msg: String) {
        startActivity<MainActivity>()
        finish()
    }

    override fun onSuccessGetLogin(data: List<DataLogin>?) {

    }

    override fun onFailedGetLogin(msg: String) {

    }

    override fun onErrorUpdate(msg: String) {}

    override fun onSuccessGet(data: List<DataItem>?) {}

    override fun onFailedGet(msg: String) {}

    override fun onSuccessDelete(msg: String) {}

    override fun onErrorDelete(msg: String) {}
}