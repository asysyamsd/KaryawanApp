package com.example.karyawanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var mUsername : EditText
    private lateinit var mPassword : EditText
    private lateinit var tvForgotPassword : TextView
    private lateinit var mBtn : Button
    private lateinit var mBtnToRegister : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUsername = findViewById(R.id.inputNama)
        mPassword = findViewById(R.id.inputPassword)
        mBtn = findViewById(R.id.btnLogin)
        mBtnToRegister = findViewById(R.id.btnToRegister)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)

        mBtn.setOnClickListener {

            if(mUsername.isEmpty() || mPassword.isEmpty()){

                // Form is empty
                Toast.makeText(this, "Silahkan isi form terlebih dahulu", Toast.LENGTH_SHORT).show()

            } else if(mUsername.text.toString().equals("admin") && mPassword.text.toString().equals("123")) {

                // Login success
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {

                // Login failed
                Toast.makeText(this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }

        tvForgotPassword.setOnClickListener {
            val mSavePw = Intent(this, ForgotPwActivity::class.java)
            startActivity(mSavePw)
        }

        mBtnToRegister.setOnClickListener {
            val mRegister = Intent(this, RegisterActivity::class.java)
            startActivity(mRegister)
        }
    }
}

private fun EditText.isEmpty(): Boolean {
    return this.text.toString().trim().isEmpty()
}