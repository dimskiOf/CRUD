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
import androidx.core.widget.addTextChangedListener
import com.komputerisasi.sprinter.InventoryKantor
import com.komputerisasi.sprinter.LoginUtama
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.Scanning.ScannCheckStokItemKantor
import com.komputerisasi.sprinter.adapter.ItemKantorStokAdapter
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_add_item_kantor.*
import kotlinx.android.synthetic.main.activity_checkstokitemkantor.*
import kotlinx.android.synthetic.main.activity_checkstokitemkantor.btnTambah
import kotlinx.android.synthetic.main.activity_checkstokitemkantor.rvCategory
import kotlinx.android.synthetic.main.activity_checkstokitemkantor.tambahManual
import org.jetbrains.anko.startActivity
import java.lang.Float

import java.util.*

class CheckStokItemKantor : AppCompatActivity(), CrudView {

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
    var autotextsearch2: AutoCompleteTextView? = null

    var menu: Menu? = null

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
        setContentView(R.layout.activity_checkstokitemkantor)

        presenter = Presenter(this)
        val tes = ""
        presenter.getDataItemKantor(this,tes, limitstart.toString(), limitend.toString())

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Data Stok"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        btnTambah.setOnClickListener{
            onAddButtonClicked()
        }

        tambahManual.setOnClickListener{
            isclick = true
            setContentView(R.layout.activity_add_item_kantor)

            val actionbar = supportActionBar

            actionbar!!.title = "ADD NEW ITEM"

            actionbar.setDisplayHomeAsUpEnabled(true)

            etKategori.addTextChangedListener ( object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {

                }
            } )

            etUnit1.addTextChangedListener( object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    presenter.getDataItemBySearch(applicationContext, s.toString())

                }
            })

            btnAction.setOnClickListener {
                val builder = AlertDialog.Builder(this@CheckStokItemKantor)
                builder.setMessage("Simpan Data?")
                    .setCancelable(false)
                    .setNegativeButton("No") { dialog, id ->

                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { dialog, id ->

                        presenter.addDataFgKeluar(
                            applicationContext,
                            etItemNos?.text.toString()
                            //etTglTransaksi?.text.toString(),
                            //Float.parseFloat(etQtyInput?.text.toString()),
                            //etCatatan?.text.toString(),
                            //etLotNumber?.text.toString(),
                            //Float.parseFloat(etInputMinusPlus?.text.toString()),
                        )


                    }
                val alert = builder.create()
                alert.show()
            }

        }

        btnScann.setOnClickListener {
            startActivity<ScannCheckStokItemKantor>()
            finish()
        }
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
            btnScann.visibility = View.VISIBLE
        }else{
            tambahManual.visibility = View.INVISIBLE
            btnScann.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            tambahManual.startAnimation(fromButon)
            btnScann.startAnimation(fromButon)
            btnTambah.startAnimation(rotateOpen)
        }else{
            tambahManual.startAnimation(toButon)
            btnScann.startAnimation(toButon)
            btnTambah.startAnimation(rotateClose)
        }
    }

    private fun setClickAble(clicked: Boolean){
        if(!clicked){
            btnScann.isClickable = true
            tambahManual.isClickable = true
        }else{
            btnScann.isClickable = false
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
        presenter.getDataItemKantor(this, searchText.toString(), limitstart.toString(), limitend.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.buttonsearch) {
            // do something here
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<InventoryKantor>()
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

    override fun onSuccessGetLogin(data: List<DataLogin>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetLogin(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessgetToken(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedgetToken(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgMasuk(data: List<FgMasukItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgMasuk(msg: String) {
        TODO("Not yet implemented")
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

    override fun onSuccessDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgMasuk(msg: String) {
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

    override fun successAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun errorAddFgMasuk(msg: String) {
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

    override fun onSuccessUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateFgMasuk(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateFgMasuk(msg: String) {
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

    override fun onSuccessGetItemById(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemById(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetItemQuery(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemQuery(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessPingApi(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorPingApi(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
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
        rvCategory.adapter = ItemKantorStokAdapter(data,object : ItemKantorStokAdapter.onClickItem{
            override fun clicked(item: DataItemKantor?) {
                val builder = AlertDialog.Builder(this@CheckStokItemKantor)
                builder.setMessage("Edit Data?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        startActivity<UpdateAddItemKantor>("dataItem" to item)
                        finish()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }

            override fun delete(item:DataItemKantor?) {
                val builder = AlertDialog.Builder(this@CheckStokItemKantor)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // Delete selected note from database
                        presenter.hapusDataItemKantor(applicationContext,item?.ItemCode)
                        startActivity<CheckStokItemKantor>()
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

    override fun onErrorGetdataItemKantor(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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