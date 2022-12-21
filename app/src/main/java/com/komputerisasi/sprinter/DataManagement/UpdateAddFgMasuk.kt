package com.komputerisasi.sprinter.DataManagement

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.komputerisasi.sprinter.LoginUtama
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_update_add_fg_masuk.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.lang.Float
import java.text.SimpleDateFormat
import java.util.*

@Suppress("SENSELESS_COMPARISON")
class UpdateAddFgMasuk : AppCompatActivity(), CrudView {
    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")

    var adjusttgl: Button? = null
    var viewdate: EditText? = null
    var calen = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        val loaduser = selectDatabase("datauser")

        if(loaduser.isNotEmpty()){

        }else{
            startActivity<LoginUtama>()
            finish()
        }

        LoginUtama.globalVar = selectDatabase("settingurl")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add_fg_masuk)

        val actionbar = supportActionBar

        presenter = Presenter(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")
        val itemcode=intent.getStringExtra("itemcode")
        val itemdes=intent.getStringExtra("itemdes")
        val tglmasuk=intent.getStringExtra("tglmasuk")
        val satuan=intent.getStringExtra("satuan")
        val catatan=intent.getStringExtra("catatan")
        val minusplus=intent.getStringExtra("minusplus")
        val quantity = intent.getStringExtra("quantity")
        val minimumqty = intent.getStringExtra("minimumqty")

        if (itemDataItem == null){
            actionbar!!.title = "SIMPAN FG MASUK"
            //set back button
            actionbar.setDisplayHomeAsUpEnabled(true)
            etItemNos.setFocusable(false)
            etItemDescription.setFocusable(false)
            etUnit1.setFocusable(false)
            etTglTransaksi.setFocusable(false)
            etQtyMinimum.setFocusable(false)
            etQuantity.setFocusable(false)

            etItemNos.setText(itemcode)
            etItemDescription.setText(itemdes)
            etInputMinusPlus.setText(minusplus)
            etUnit1.setText(satuan)
            etCatatan.setText(catatan)
            etTglTransaksi.setText(tglmasuk)
            etQtyMinimum.setText(minimumqty)
            etQuantity.setText(quantity)

            adjusttgl = this.adjusttgltransaksi
            viewdate = this.etTglTransaksi

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                       dayOfMonth: Int) {
                    calen.set(Calendar.YEAR, year)
                    calen.set(Calendar.MONTH, monthOfYear)
                    calen.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()
                }
            }

            val timeSetListener = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker, hourofday: Int, minute: Int) {
                    calen.set(Calendar.HOUR_OF_DAY, hourofday)
                    calen.set(Calendar.MINUTE, minute)
                    updateDateInView()
                }
            }

            adjusttgl!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    TimePickerDialog(this@UpdateAddFgMasuk,
                        timeSetListener,
                        calen.get(Calendar.HOUR_OF_DAY),
                        calen.get(Calendar.MINUTE),true).show()

                    DatePickerDialog(this@UpdateAddFgMasuk,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        calen.get(Calendar.YEAR),
                        calen.get(Calendar.MONTH),
                        calen.get(Calendar.DAY_OF_MONTH)).show()
                }

            })

            btnAction.text = "Simpan"
            btnAction.onClick {
                val builder = AlertDialog.Builder(this@UpdateAddFgMasuk)
                builder.setMessage("Simpan Data?")
                    .setCancelable(false)
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        presenter.addDataFgMasuk(
                            applicationContext,etItemNos.text.toString(),
                            etTglTransaksi.text.toString(),
                            Float.parseFloat(etQtyInput.text.toString()),
                            etCatatan.text.toString(),
                            etLotNumber.text.toString(),
                            Float.parseFloat(etInputMinusPlus.text.toString()),
                        )
                    }
                val alert = builder.create()
                alert.show()

            }

        }else if (itemDataItem != null){
            actionbar!!.title = "UPDATE FG MASUK"
            //set back button
            actionbar.setDisplayHomeAsUpEnabled(true)
            btnAction.text = "Update"
            val item = itemDataItem as FgMasukItem?
            etItemNos.setFocusable(false)
            etItemDescription.setFocusable(false)
            etUnit1.setFocusable(false)
            etTglTransaksi.setFocusable(false)
            etQtyMinimum.setFocusable(false)
            etQuantity.setFocusable(false)

            etItemNos.setText(item?.ItemNo.toString())
            etItemDescription.setText(item?.ItemDescription.toString())
            etQtyInput.setText(item?.Qty.toString())
            etLotNumber.setText(item?.LotNumber.toString())
            etInputMinusPlus.setText(item?.InputMinusPlus.toString())
            if (item?.Unit3.toString() != null){
                etUnit1.setText(item?.Unit3.toString())
            }else {
                etUnit1.setText(item?.Unit1.toString())
            }
            etTglTransaksi.setText(item?.TglCatatan.toString())
            btnAction.onClick {
                val builder = AlertDialog.Builder(this@UpdateAddFgMasuk)
                builder.setMessage("Update Data?")
                    .setCancelable(false)
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        presenter.updateDataFgMasuk(
                            applicationContext,item?.Id_warehouse_InOut ?: "",
                            etItemNos.text.toString(),
                            etTglTransaksi.text.toString(),
                            Float.parseFloat(etQtyInput.text.toString()),
                            etCatatan.text.toString(),
                            etLotNumber.text.toString(),
                            Float.parseFloat(etInputMinusPlus.text.toString())
                        )
                        finish()
                    }
                val alert = builder.create()
                alert.show()

            }

        }
    }

    override fun onSuccessGetItemQuery(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemQuery(msg: String) {
        TODO("Not yet implemented")
    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd hh:mm:ss" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        viewdate!!.setText(sdf.format(calen.getTime()))
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<FgMasuk>()
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        startActivity<FgMasuk>()
        finish()
    }

    override fun onSuccessDeleteFgMasuk(msg: String) {
    }

    override fun onSuccessGetFgMasuk(data: List<FgMasukItem>?) {
    }

    override fun onSuccessUpdateFgMasuk(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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