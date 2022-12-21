package com.komputerisasi.sprinter.Scanning

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.komputerisasi.sprinter.DataManagement.CheckStok
import com.komputerisasi.sprinter.DataManagement.CheckStokItemKantor
import com.komputerisasi.sprinter.DataManagement.HasilScan
import com.komputerisasi.sprinter.DataManagement.HasilScanItemKantor
import com.komputerisasi.sprinter.LoginUtama
import com.komputerisasi.sprinter.R
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_scann_check_stok.*
import org.jetbrains.anko.startActivity

class ScannCheckStokItemKantor : AppCompatActivity(), CrudView {
    @SuppressLint("SetTextI18n")
    private lateinit var codeScanner: CodeScanner

    private val heavyObject: Presenter by lazy {
        Presenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val loaduser = selectDatabase("datauser")

        if(loaduser.isNotEmpty()){

        }else{
            startActivity<LoginUtama>()
            finish()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scann_checkstok_item_kantor)

        LoginUtama.globalVar = selectDatabase("settingurl")

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "SCANNING BARCODE/QRCODE"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        setupPermissions()
        codeScanner()
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<CheckStokItemKantor>()
        finish()
        return true
    }

    fun codeScanner() {
        codeScanner = CodeScanner(this, scn)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    hasilscanncheckstok.text = it.text
                    if (it.text.toString() == "" )
                    {

                    }else{
                        codeScanner.releaseResources()
                        super.onPause()
                        heavyObject.getDataItemKantorGetItemById(
                            applicationContext,
                            it.text.toString()
                        )
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    //  Log.e("Main", "codeScanner: ${it.message}")
                }
            }

            scn.setOnClickListener {
                codeScanner.startPreview()
            }

        }
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

    override fun onResume() {

        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {


        codeScanner.releaseResources()
        super.onPause()
    }



    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQ
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQ -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "You need the camera permission to use this app",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val CAMERA_REQ = 101
    }

    override fun onSuccessGetItemQuery(data: List<GetItemById>?) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetItemQuery(msg: String) {
        TODO("Not yet implemented")
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
    }

    override fun onErrorGetItemById(msg: String) {
    }

    override fun onSuccessGetDBname(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorGetDBname(msg: String) {
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

    }

    override fun onFailedGetScanChekStok(msg: String) {

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
        data?.forEach{
                i ->
            val b = Intent(this, HasilScanItemKantor::class.java)
            b.putExtra("itemcode", i.ItemCode)
            b.putExtra("itemdes", i.Itemdes)
            b.putExtra("satuan", i.Unit1)
            b.putExtra("quantity", i.Quantity)
            b.putExtra("minimumqty", i.Minimumqty)
            startActivity(b)
            finish()
        }
    }

    override fun onErrorGetDataItemKantorById(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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