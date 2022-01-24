package com.komputerisasi.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komputerisasi.crud.DataManagement.FgKeluar
import com.komputerisasi.crud.DataManagement.FgMasuk
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FgKeluar.setOnClickListener {
            startActivity<FgKeluar>()
            finish()
        }
        FgMasuk.setOnClickListener {
            startActivity<FgMasuk>()
            finish()
        }
    }
}