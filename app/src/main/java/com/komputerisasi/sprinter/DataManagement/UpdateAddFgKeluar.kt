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
import kotlinx.android.synthetic.main.activity_update_add_fg_keluar.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.lang.Float
import java.text.SimpleDateFormat
import java.util.*

@Suppress("SENSELESS_COMPARISON")
class UpdateAddFgKeluar : AppCompatActivity(), CrudView {

    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    private lateinit var presenter: Presenter

    var adjusttgl: Button? = null
    var viewdate: EditText? = null
    var calen = Calendar.getInstance()

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
        setContentView(R.layout.activity_update_add_fg_keluar)

        val actionbar = supportActionBar
        //set actionbar title

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
            actionbar!!.title = "SIMPAN FG KELUAR"
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
                    TimePickerDialog(this@UpdateAddFgKeluar,
                        timeSetListener,
                        calen.get(Calendar.HOUR_OF_DAY),
                        calen.get(Calendar.MINUTE),true).show()

                    DatePickerDialog(this@UpdateAddFgKeluar,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        calen.get(Calendar.YEAR),
                        calen.get(Calendar.MONTH),
                        calen.get(Calendar.DAY_OF_MONTH)).show()
                }

            })

            btnAction.text = "Simpan"
            btnAction.onClick {
                if ((minimumqty.toString() == null) or (minimumqty.toString() == "0")) {
                    if(Float.parseFloat(quantity.toString()) >= Float.parseFloat(etQtyInput.text.toString())){
                        val builder = AlertDialog.Builder(this@UpdateAddFgKeluar)
                        builder.setMessage("Simpan Data?")
                            .setCancelable(false)
                            .setNegativeButton("No") { dialog, id ->

                                dialog.dismiss()
                            }
                            .setPositiveButton("Yes") { dialog, id ->

                                presenter.addDataFgKeluar(
                                    applicationContext, etItemNos.text.toString(),
                                    etTglTransaksi.text.toString(),
                                    Float.parseFloat(etQtyInput.text.toString()),
                                    etCatatan.text.toString(),
                                    etLotNumber.text.toString(),
                                    Float.parseFloat(etInputMinusPlus.text.toString()),
                                )
                            }
                        val alert = builder.create()
                        alert.show()
                    }else{
                        Toast.makeText(applicationContext, "Qauntity request melebihi stok awal", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if(Float.parseFloat(quantity.toString()) >= Float.parseFloat(etQtyInput.text.toString())){
                        val a = Float.parseFloat(quantity.toString())
                        val b = Float.parseFloat(etQtyInput.text.toString())
                       if (Float.parseFloat(minimumqty.toString()) >= (a-b)){
                           val builder = AlertDialog.Builder(this@UpdateAddFgKeluar)
                           builder.setMessage("Stok awal akan membawahi quantity minimum, ingin melanjutkan?")
                               .setCancelable(false)
                               .setNegativeButton("No") { dialog, id ->

                                   dialog.dismiss()
                               }
                               .setPositiveButton("Yes") { dialog, id ->

                                   presenter.addDataFgKeluar(
                                       applicationContext, etItemNos.text.toString(),
                                       etTglTransaksi.text.toString(),
                                       Float.parseFloat(etQtyInput.text.toString()),
                                       etCatatan.text.toString(),
                                       etLotNumber.text.toString(),
                                       Float.parseFloat(etInputMinusPlus.text.toString()),
                                   )
                               }
                           val alert = builder.create()
                           alert.show()
                       }else{
                           val builder = AlertDialog.Builder(this@UpdateAddFgKeluar)
                           builder.setMessage("Simpan Data?")
                               .setCancelable(false)
                               .setNegativeButton("No") { dialog, id ->

                                   dialog.dismiss()
                               }
                               .setPositiveButton("Yes") { dialog, id ->

                                   presenter.addDataFgKeluar(
                                       applicationContext, etItemNos.text.toString(),
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
                    }else{
                        Toast.makeText(applicationContext, "Qauntity request melebihi stok awal", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }else if (itemDataItem != null){
            actionbar!!.title = "UPDATE FG KELUAR"
            //set back button
            actionbar.setDisplayHomeAsUpEnabled(true)
            btnAction.text = "Update"
            val item = itemDataItem as FgKeluarItem?
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
                val builder = AlertDialog.Builder(this@UpdateAddFgKeluar)
                builder.setMessage("Update Data?")
                    .setCancelable(false)
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        presenter.updateDataFgKeluar(
                            applicationContext,item?.Id_warehouse_InOut ?: "",
                            etItemNos.text.toString(),
                            etTglTransaksi.text.toString(),
                            Float.parseFloat(etQtyInput.text.toString()),
                            etCatatan.text.toString(),
                            etLotNumber.text.toString(),
                            Float.parseFloat(etInputMinusPlus.text.toString()))
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
        startActivity<FgKeluar>()
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        startActivity<FgKeluar>()
        finish()
    }

    override fun errorAddFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        startActivity<FgKeluar>()
        finish()
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
}