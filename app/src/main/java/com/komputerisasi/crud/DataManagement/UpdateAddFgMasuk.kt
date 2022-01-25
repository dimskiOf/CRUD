package com.komputerisasi.crud.DataManagement

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.crud.R
import com.komputerisasi.crud.model.*
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_update_add_fg_masuk.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class UpdateAddFgMasuk : AppCompatActivity(), CrudView {


    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add_fg_masuk)

        presenter = Presenter(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        if (itemDataItem == null){
            btnAction.text = "Simpan"
            btnAction.onClick {
                presenter.addDataFgMasuk(
                    etItemNo.text.toString(),
                    etTglCreateFg.text.toString(),
                    Integer.parseInt(etQtyFG.text.toString()),
                    etLoadNumber.text.toString(),
                    etInputMinusPlus.text.toString(),
                )
            }

        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as FgMasukItem?
            etItemNo.setText(item?.ItemNo.toString())
            etItemDescription.setText(item?.ItemDescription.toString())
            etQtyFG.setText(item?.QtyFG.toString())
            etLoadNumber.setText(item?.LoadNumber.toString())
            etInputMinusPlus.setText(item?.InputMinusPlus.toString())
            etUnit1.setText(item?.Unit1.toString())
            etTglCreateFg.setText(item?.TglCreateFg.toString())
            btnAction.onClick {
                presenter.updateDataFgMasuk(
                    item?.IdFgMasuk ?: "",
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
    }

    override fun errorAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }


    override fun onSuccessGetLogin(data: List<DataLogin>?) {

    }

    override fun onFailedGetLogin(msg: String) {

    }

    override fun successAddFgMasuk(msg: String) {
        startActivity<FgMasuk>()
        finish()
    }

    override fun onSuccessDeleteFgMasuk(msg: String) {
    }

    override fun onSuccessGetFgMasuk(data: List<FgMasukItem>?) {
    }

    override fun onSuccessUpdateFgMasuk(msg: String) {
        startActivity<FgMasuk>()
        finish()
    }
    override fun errorAddFgMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onErrorDeleteFgMasuk(msg: String) {
    }

    override fun onErrorUpdateFgMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
        TODO("Not yet implemented")
    }
}