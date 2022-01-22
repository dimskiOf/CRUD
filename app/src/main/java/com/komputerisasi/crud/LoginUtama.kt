package com.komputerisasi.crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.crud.adapter.LoginAdapter
import com.komputerisasi.crud.model.DataItem
import com.komputerisasi.crud.model.DataLogin
import com.komputerisasi.crud.presenter.CrudView
import com.komputerisasi.crud.presenter.Presenter
import kotlinx.android.synthetic.main.activity_login_utama.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_add.*
import org.jetbrains.anko.startActivity
import android.content.Intent

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

    override fun errorAdd(msg: String) {
    }

    override fun onErrorDelete(msg: String) {
    }

    override fun onErrorUpdate(msg: String) {
    }

    override fun onFailedGet(msg: String) {
    }

    override fun onSuccessDelete(msg: String) {
    }

    override fun onSuccessGet(data: List<DataItem>?) {
    }

    override fun onSuccessUpdate(msg: String) {
    }

    override fun successAdd(msg: String) {
    }
}