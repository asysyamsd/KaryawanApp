package com.example.karyawanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ForgotPwActivity : AppCompatActivity() {
    private lateinit var mNewPassword : EditText
    private lateinit var mConfirmPassword : EditText
    private lateinit var mBtnSave : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pw)

        mNewPassword = findViewById(R.id.inputNewPassword)
        mConfirmPassword = findViewById(R.id.confirmNewPassword)
        mBtnSave = findViewById(R.id.btnSavePw)

        mBtnSave.setOnClickListener {

            if (mNewPassword.isEmpty()) {
                // Password kosong
                Toast.makeText(this, "Silahkan isi Password terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                // EditText 1 berisi string, jalankan main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

private fun EditText.isEmpty(): Boolean {
    return this.text.toString().trim().isEmpty()
}
