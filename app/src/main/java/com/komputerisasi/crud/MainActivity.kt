package com.komputerisasi.crud

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.komputerisasi.crud.DataManagement.FgKeluar
import com.komputerisasi.crud.DataManagement.FgMasuk
import com.komputerisasi.crud.DataManagement.RmKeluar
import com.komputerisasi.crud.DataManagement.RmMasuk
import com.komputerisasi.crud.konfigurasi.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val data1 = selectDatabase("username")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoginUtama.globalVar = selectDatabase("settingurl")

        selamatdatang.setText(data1)

        FgKeluar.setOnClickListener {
            startActivity<FgKeluar>()
            finish()
        }
        FgMasuk.setOnClickListener {
            startActivity<FgMasuk>()
            finish()
        }
        RmKeluar.setOnClickListener {
            startActivity<RmKeluar>()
            finish()
        }
        RmMasuk.setOnClickListener {
            startActivity<RmMasuk>()
            finish()
        }

        Logout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    // Delete selected note from database
                    deletesDatabase("username")
                    deletesDatabase("accesstoken")
                    val intent = Intent(this, LoginUtama::class.java)
                    intent.putExtra(
                        "finish",
                        true
                    )

                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()

                    Toast.makeText(this, "Logout!!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

    }
    private fun deletesDatabase(namaseting: String) {
        var helper = DatabaseHelper(applicationContext)
        var db = helper.readableDatabase
        db.execSQL("delete from table_settings where name_setting = '"+namaseting+"'")
        db.close()
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
}