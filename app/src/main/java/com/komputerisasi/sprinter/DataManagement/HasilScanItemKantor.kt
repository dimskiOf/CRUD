package com.komputerisasi.sprinter.DataManagement

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komputerisasi.sprinter.LoginUtama
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_hasil_scan.*
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class HasilScanItemKantor : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        val loaduser = selectDatabase("datauser")

        if(loaduser.isNotEmpty()){

        }else{
            startActivity<LoginUtama>()
            finish()
        }

        LoginUtama.globalVar = selectDatabase("settingurl")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_scan_item_kantor)

        val actionbar = supportActionBar
        //set actionbar title

        etItemNos.setFocusable(false)
        etItemDescription.setFocusable(false)
        etUnit1.setFocusable(false)
        etQtyMinimum.setFocusable(false)
        etQuantity.setFocusable(false)

        presenter = Presenter(this)
        val itemcode=intent.getStringExtra("itemcode")
        val itemdes=intent.getStringExtra("itemdes")
        val satuan=intent.getStringExtra("satuan")
        val quantity = intent.getStringExtra("quantity")
        val minimumqty = intent.getStringExtra("minimumqty")

        etItemNos.setText(itemcode)
        etItemDescription.setText(itemdes)
        etUnit1.setText(satuan)
        etQtyMinimum.setText(minimumqty)
        etQuantity.setText(quantity)

        actionbar!!.title = "HASIL SCAN"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<CheckStokItemKantor>()
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
    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
        TODO("Not yet implemented")
    }
    override fun onSuccessGetItemQuery(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemQuery(msg: String) {
        TODO("Not yet implemented")
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
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
    }

    override fun onErrorUpdateFgKeluar(msg: String) {

    }


    override fun onSuccessGetLogin(data: List<DataLogin>?) {

    }

    override fun onFailedGetLogin(msg: String) {

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

    override fun onSuccessGetItemById(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemById(msg: String) {
        TODO("Not yet implemented")
    }
    override fun onSuccessPingApi(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorPingApi(msg: String) {
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

    override fun onSuccessGetDataItemKantor(data: List<DataItemKantor>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetdataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemKantorKeluar(data: List<DataItemKantorKeluar>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemKantorMasuk(data: List<DataItemKantorMasuk>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemKantorById(data: List<DataItemKantorGetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataItemKantorById(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddDataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorAddDataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorAddDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorAddDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateDataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateDataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteDataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteDataItemKantor(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteDataItemKantorKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteDataItemKantorMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemSparepart(data: List<DataItemSparepart>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetdataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemSparepartKeluar(data: List<DataItemSparepartKeluar>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemSparepartMasuk(data: List<DataItemSparepartMasuk>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDataItemSparepartById(data: List<DataItemSparepartGetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDataItemSparepartById(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddDataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorAddDataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorAddDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorAddDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateDataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateDataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteDataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteDataItemSparepart(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteDataItemSparepartKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteDataItemSparepartMasuk(msg: String) {
        TODO("Not yet implemented")
    }
}