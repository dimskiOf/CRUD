package com.komputerisasi.crud.DataManagement

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komputerisasi.crud.R
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.model.FgKeluarItem
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_update_add.btnAction
import kotlinx.android.synthetic.main.activity_update_add_fg_keluar.*
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
            btnAction.text = "Simpan"
            btnAction.onClick {
                presenter.addDataFgKeluar(
                    etItemNo.text.toString(),
                    etTglCreateFg.text.toString(),
                    Integer.parseInt(etQtyFG.text.toString()),
                    etLoadNumber.text.toString(),
                    etInputMinusPlus.text.toString(),
                    )
            }

        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as FgKeluarItem?
            etItemNo.setText(item?.ItemNo.toString())
            etItemDescription.setText(item?.ItemDescription.toString())
            etQtyFG.setText(item?.QtyFG.toString())
            etLoadNumber.setText(item?.LoadNumber.toString())
            etInputMinusPlus.setText(item?.InputMinusPlus.toString())
            etUnit1.setText(item?.Unit1.toString())
            etTglCreateFg.setText(item?.TglCreateFg.toString())
            btnAction.onClick {
                presenter.updateDataFgKeluar(
                    item?.IdFgKeluar ?: "",
                    etItemNo.text.toString(),
                    etTglCreateFg.text.toString(),
                    Integer.parseInt(etQtyFG.text.toString()),
                    etLoadNumber.text.toString(),
                    etInputMinusPlus.text.toString())
                finish()
            }

        }
    }


    override fun onSuccessDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        TODO("Not yet implemented")
    }



    override fun successAddFgKeluar(msg: String) {
        startActivity<FgKeluar>()
        finish()
    }

    override fun errorAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
        startActivity<FgKeluar>()
        finish()
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }


    override fun onSuccessGetLogin(data: List<DataLogin>?) {

    }

    override fun onFailedGetLogin(msg: String) {

    }

}