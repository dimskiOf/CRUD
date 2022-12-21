package com.komputerisasi.sprinter.DataManagement

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.SearchManager
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.sprinter.InventoryGudang
import com.komputerisasi.sprinter.LoginUtama
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.Scanning.ScannFgKeluar
import com.komputerisasi.sprinter.adapter.FgKeluarAdapter
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_add_keluar_masuk_barang_manual.*
import kotlinx.android.synthetic.main.activity_fg_keluar.*
import org.jetbrains.anko.startActivity
import java.lang.Float
import java.text.SimpleDateFormat
import java.util.*


class FgKeluar : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    companion object {
        var limitstart = 0
        var limitend = 10
    }

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)}
    private val fromButon: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_button_anim)}
    private val toButon: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_button_anim)}

    var autotextsearch: AutoCompleteTextView? = null
    
    var menu: Menu? = null
    var adjusttgl: Button? = null
    var viewdate: EditText? = null
    var calen = Calendar.getInstance()

    private var clicked = false

    private var isclick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val loaduser = selectDatabase("datauser")

        if(loaduser.isNotEmpty()){

        }else{
            startActivity<LoginUtama>()
            finish()
        }

        LoginUtama.globalVar = selectDatabase("settingurl")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fg_keluar)
        presenter = Presenter(this)
        val tes = ""
        presenter.getDataFgKeluar(this,tes, limitstart.toString(), limitend.toString())

        val actionbar = supportActionBar
        actionbar!!.title = "BARANG JADI KELUAR"

        actionbar.setDisplayHomeAsUpEnabled(true)

        btnTambah.setOnClickListener {
            onAddButtonClicked()
        }

        tambahManual.setOnClickListener{
            isclick = true
            setContentView(R.layout.activity_add_keluar_masuk_barang_manual)

            val actionbar = supportActionBar

            actionbar!!.title = "FG KELUAR INPUT"

            actionbar.setDisplayHomeAsUpEnabled(true)

            etItemDescription.setFocusable(false)
            etQuantity.setFocusable(false)
            etQtyMinimum.setFocusable(false)
            etUnit1.setFocusable(false)
            etTglTransaksi.setFocusable(false)

            etItemNos.addTextChangedListener( object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    presenter.getDataItemBySearch(applicationContext, s.toString())

                }
                })

            adjusttgl = this.adjusttgltransaksi
            viewdate = this.etTglTransaksi
            val timing = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

            viewdate!!.setText(timing.format(Date()))

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
                    TimePickerDialog(this@FgKeluar,
                        timeSetListener,
                        calen.get(Calendar.HOUR_OF_DAY),
                        calen.get(Calendar.MINUTE),true).show()

                    DatePickerDialog(this@FgKeluar,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        calen.get(Calendar.YEAR),
                        calen.get(Calendar.MONTH),
                        calen.get(Calendar.DAY_OF_MONTH)).show()
                }

            })

                btnAction.setOnClickListener {
                    val builder = AlertDialog.Builder(this@FgKeluar)
                    builder.setMessage("Simpan Data?")
                        .setCancelable(false)
                        .setNegativeButton("No") { dialog, id ->

                            dialog.dismiss()
                        }
                        .setPositiveButton("Yes") { dialog, id ->

                            presenter.addDataFgKeluar(
                                applicationContext,
                                etItemNos?.text.toString(),
                                etTglTransaksi?.text.toString(),
                                Float.parseFloat(etQtyInput?.text.toString()),
                                etCatatan?.text.toString(),
                                etLotNumber?.text.toString(),
                                Float.parseFloat(etInputMinusPlus?.text.toString()),
                            )


                        }
                    val alert = builder.create()
                    alert.show()
                }

        }
        tambahScan.setOnClickListener {
            startActivity<ScannFgKeluar>()
            finish()
        }
    }

    override fun onSuccessGetItemQuery(data: List<GetItemById>?) {
        val setter: MutableList<String> = ArrayList()
        data?.forEach { i ->
            setter.add(i.ItemCode.toString())

        }
        autotextsearch = this.findViewById(R.id.etItemNos);

        val adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, setter as List<Any?>)

        autotextsearch?.setAdapter(adapter)
        autotextsearch?.setThreshold(1)

        autotextsearch?.setOnItemClickListener{
                parent, view, position, id ->
                presenter.getDataItemById(applicationContext,etItemNos.text.toString())
        }
    }

    override fun onErrorGetItemQuery(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd hh:mm:ss" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        viewdate!!.setText(sdf.format(calen.getTime()))
    }


    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickAble(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean){
        if(!clicked){
            tambahManual.visibility = View.VISIBLE
            tambahScan.visibility = View.VISIBLE
        }else{
            tambahManual.visibility = View.INVISIBLE
            tambahScan.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            tambahManual.startAnimation(fromButon)
            tambahScan.startAnimation(fromButon)
            btnTambah.startAnimation(rotateOpen)
        }else{
            tambahManual.startAnimation(toButon)
            tambahScan.startAnimation(toButon)
            btnTambah.startAnimation(rotateClose)
        }
    }

    private fun setClickAble(clicked: Boolean){
        if(!clicked){
            tambahScan.isClickable = true
            tambahManual.isClickable = true
        }else{
            tambahScan.isClickable = false
            tambahManual.isClickable =false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(!isclick) {
            menuInflater.inflate(R.menu.mymenu, menu)
            val searchView = menu?.findItem(R.id.buttonsearch)?.actionView as SearchView
            val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName)
            )

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    getInput(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    return false
                }
            })
        }
            return true
            return super.onCreateOptionsMenu(menu)
    }
    fun getInput(searchText: String?) {
        presenter.getDataFgKeluar(this,searchText.toString(), limitstart.toString(), limitend.toString())
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.buttonsearch) {
            // do something here
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        if(!isclick) {
            startActivity<InventoryGudang>()
            finish()
        }else{
            startActivity<FgKeluar>()
            finish()
            isclick = false
        }
            return true

    }

    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
        TODO("Not yet implemented")
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

    override fun onSuccessGetLogin(data: List<DataLogin>?) {}

    override fun onFailedGetLogin(msg: String) {
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        
        rvCategory.adapter = FgKeluarAdapter(data,object : FgKeluarAdapter.onClickItem{
            override fun clicked(item: FgKeluarItem?) {
                val builder = AlertDialog.Builder(this@FgKeluar)
                builder.setMessage("Edit Data?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        startActivity<UpdateAddFgKeluar>("dataItem" to item)
                        finish()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }

            override fun delete(item: FgKeluarItem?) {
                val builder = AlertDialog.Builder(this@FgKeluar)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        presenter.hapusDataFgKeluar(applicationContext,item?.Id_warehouse_InOut)
                        startActivity<FgKeluar>()
                        finish()

                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

            }
        })
    }

    override fun onFailedGetFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessDeleteFgKeluar(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        startActivity<FgKeluar>()
        finish()
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessPingApi(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorPingApi(msg: String) {
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

    override fun errorAddRmMasuk(msg: String) {
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
        data?.forEach { i ->
            etItemDescription.setText(i.Itemdes)
            etQuantity.setText(i.Quantity)
            etQtyMinimum.setText(i.Minimumqty)
            etUnit1.setText(i.Satuan)
        }
        etInputMinusPlus.setText("0")
        etQtyInput.setText("0")

    }

    override fun onErrorGetItemById(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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

    override fun onSuccessGetKategoryItemLike(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetKategoryItemLike(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetSatuanItemLike(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetSatuanItemLike(msg: String) {
        TODO("Not yet implemented")
    }
}