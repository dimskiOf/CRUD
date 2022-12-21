package com.komputerisasi.sprinter

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.komputerisasi.sprinter.konfigurasi.DatabaseHelper
import com.komputerisasi.sprinter.model.*
import com.komputerisasi.sprinter.presenter.CrudView
import com.komputerisasi.sprinter.presenter.Presenter
import kotlinx.android.synthetic.main.activity_profil_pengguna.*
import okhttp3.internal.and
import org.jetbrains.anko.startActivity
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class ProfilPengguna : AppCompatActivity(),CrudView {
     var txtNameLengkap: TextView? = null
     var btnOpenDialogNamaLengkap: Button? = null

     var txtUsername: TextView? = null
     var btnOpenDialogUsername: Button? = null

     var txtPassword: TextView? = null
     var btnOpenDialogPassword: Button? = null

     var txtNohp: TextView? = null
     var btnOpenDialogNohp: Button? = null

     var customDialognamalengkap: Dialog? = null
     var txtInputnamalengkap: EditText? = null
     var btnInsertNamalengkap: Button? = null

    var customDialogusername: Dialog? = null
    var txtInputusername: EditText? = null
    var btnInsertusername: Button? = null

    var customDialogpassword: Dialog? = null
    var txtInputpassword: EditText? = null
    var txtInputpassword2: EditText? = null
    var btnInsertpassword: Button? = null

    var customDialognohp: Dialog? = null
    var txtInputnohp: EditText? = null
    var btnInsertnohp: Button? = null

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
        setContentView(R.layout.activity_profil_pengguna)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "ATUR PROFIL"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        presenter = Presenter(this)

        presenter.getDataUser(applicationContext)

        etNamaLengkap.setFocusable(false)
        etUsername.setFocusable(false)
        etPassword.setFocusable(false)
        etJabatan.setFocusable(false)
        etNoHP.setFocusable(false)

        update_namalengkap.setOnClickListener {
            initViews_namalengkap()
        }
        update_username.setOnClickListener {
            initViews_username()
        }
        update_password.setOnClickListener {
            initViews_password()
        }
        update_nohp.setOnClickListener {
            initViews_nohp()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        startActivity<MainActivity>()
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

    private fun updateDatabase(valuess: String,namaseting: String,status: Int){
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        db.execSQL("UPDATE table_settings set valuer='"+ valuess +"',status ='"+status+"' where name_setting= '"+namaseting+"'")
        db.close()
    }

    private fun initViews_namalengkap() {
        initCustomDialog_namalengkap()
        initViewComponents_namalengkap()
    }

    private fun initCustomDialog_namalengkap() {
        customDialognamalengkap = Dialog(this@ProfilPengguna)
        customDialognamalengkap!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialognamalengkap!!.setContentView(R.layout.dialog_update_namalengkap)
        customDialognamalengkap!!.setCancelable(true)
        txtInputnamalengkap = customDialognamalengkap!!.findViewById(R.id.etNamaSubmit)
        btnInsertNamalengkap = customDialognamalengkap!!.findViewById(R.id.btnSubmit)
        btnInsertNamalengkap?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val namalengkap = txtInputnamalengkap!!.getText().toString()
                presenter.updateDataProfil(applicationContext,"tbl_user","nama",namalengkap)
                txtInputnamalengkap!!.setText("")
                customDialognamalengkap!!.dismiss()
            }
        })
    }

    private fun initViewComponents_namalengkap() {
        txtNameLengkap = findViewById<View>(R.id.etNamaLengkap) as TextView?
        btnOpenDialogNamaLengkap = findViewById<View>(R.id.update_namalengkap) as Button?
        btnOpenDialogNamaLengkap!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                customDialognamalengkap?.show()
            }
        })
    }

    private fun initViews_username() {
        initCustomDialog_username()
        initViewComponents_username()
    }

    private fun initCustomDialog_username() {
        customDialogusername = Dialog(this@ProfilPengguna)
        customDialogusername!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialogusername!!.setContentView(R.layout.dialog_update_username)
        customDialogusername!!.setCancelable(true)
        txtInputusername = customDialogusername!!.findViewById(R.id.etUsernameSubmit)
        btnInsertusername = customDialogusername!!.findViewById(R.id.btnSubmitUsername)
        btnInsertusername?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val usernme = txtInputusername!!.getText().toString()
                presenter.updateDataProfil(applicationContext,"tbl_login","username",usernme)
                txtInputusername!!.setText("")
                customDialogusername!!.dismiss()
            }
        })
    }

    private fun initViewComponents_username() {
        txtUsername = findViewById<View>(R.id.etUsername) as TextView?
        btnOpenDialogUsername = findViewById<View>(R.id.update_username) as Button?
        btnOpenDialogUsername!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                customDialogusername?.show()
            }
        })
    }

    private fun initViews_password() {
        initCustomDialog_password()
        initViewComponents_password()
    }

    private fun initCustomDialog_password() {
        customDialogpassword = Dialog(this@ProfilPengguna)
        customDialogpassword!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialogpassword!!.setContentView(R.layout.dialog_update_password)
        customDialogpassword!!.setCancelable(true)
        txtInputpassword = customDialogpassword!!.findViewById(R.id.etPasswordSubmit)
        txtInputpassword2= customDialogpassword!!.findViewById(R.id.etRePasswordSubmit)
        btnInsertpassword = customDialogpassword!!.findViewById(R.id.btnSubmitPassword)
        btnInsertpassword?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val password1 = txtInputpassword!!.getText().toString()
                val password2 = txtInputpassword2!!.getText().toString()
                if (password1 == password2){
                    val b = MD5s(password1)
                    if (b != null) {
                        presenter.updateDataProfil(applicationContext,"tbl_login","password",b)
                        txtInputpassword!!.setText("")
                        txtInputpassword2!!.setText("")
                    }
                    customDialogpassword!!.dismiss()
                }else{
                    Toast.makeText(applicationContext, "Password tidak sama", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        })
    }

    private fun initViewComponents_password() {
        txtPassword = findViewById<View>(R.id.etPassword) as TextView?
        btnOpenDialogPassword = findViewById<View>(R.id.update_password) as Button?
        btnOpenDialogPassword!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                customDialogpassword?.show()
            }
        })
    }

    private fun initViews_nohp() {
        initCustomDialog_nohp()
        initViewComponents_nohp()
    }

    private fun initCustomDialog_nohp() {
        customDialognohp = Dialog(this@ProfilPengguna)
        customDialognohp!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialognohp!!.setContentView(R.layout.dialog_nohp)
        customDialognohp!!.setCancelable(true)
        txtInputnohp = customDialognohp!!.findViewById(R.id.etNoHPSubmit)
        btnInsertnohp = customDialognohp!!.findViewById(R.id.btnPhoneSubmit)
        btnInsertnohp?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val nohp = txtInputnohp!!.getText().toString()
                presenter.updateDataProfil(applicationContext,"tbl_user","no_hp",nohp)
                txtInputnohp!!.setText("")
                customDialognohp!!.dismiss()
            }
        })
    }

    private fun initViewComponents_nohp() {
        txtNohp = findViewById<View>(R.id.etNoHP) as TextView?
        btnOpenDialogNohp = findViewById<View>(R.id.update_nohp) as Button?
        btnOpenDialogNohp!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                customDialognohp?.show()
            }
        })
    }

    private fun MD5s(md5: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            val array = md.digest(md5.toByteArray(charset("UTF-8")))
            val sb = StringBuffer()
            for (i in array.indices) {
                sb.append(Integer.toHexString(array[i] and 0xFF or 0x100).substring(1, 3))
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
        } catch (ex: UnsupportedEncodingException) {
        }
        return null
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        presenter.getDataUser(applicationContext)
    }

    override fun onErrorUpdateProfile(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessGetDataUser(data: List<DataUser>?) {
        data?.forEach{
                i ->
            etNamaLengkap.setText(i.nama)
            etUsername.setText(i.username)
            etJabatan.setText(i.privilage)
            etNoHP.setText(i.nohp)
            updateDatabase(i.username.toString()+","+i.nama.toString()+","+i.privilage.toString(),"datauser",1)
        }

    }

    override fun onErrorGetDataUser(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}