package com.komputerisasi.crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_login_utama.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_add.*
import android.content.Intent
import com.komputerisasi.crud.model.FgKeluarItem

class LoginUtama() : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_utama)

        presenter = Presenter(this)

        btnLogin.setOnClickListener {
            val email = username.text.toString()
            val password = password.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Insert Username and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                presenter.loginData(
                    email,
                    password
                )
            }
        }
    }

    override fun onSuccessGetLogin(data: List<DataLogin>?) {
        data?.forEach{
            i ->
            val b = Intent(this, MainActivity::class.java)
            b.putExtra("token", "Selamat datang "+i.username)
            startActivity(b)
        }

    }

    override fun onFailedGetLogin(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun errorAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onFailedGetFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetFgKeluar(data: List<FgKeluarItem>?) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }

    override fun successAddFgKeluar(msg: String) {
        TODO("Not yet implemented")
    }
}