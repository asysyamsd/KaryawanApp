package com.example.karyawanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var mRegUsername : EditText
    private lateinit var mRegPassword : EditText
    private lateinit var mBtnRegister : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegUsername = findViewById(R.id.inputRegUsername)
        mRegPassword = findViewById(R.id.inputRegPassword)
        mBtnRegister = findViewById(R.id.btnRegister)

        mBtnRegister.setOnClickListener {

            if(mRegUsername.isEmpty() || mRegPassword.isEmpty()){

                // Form is empty
                Toast.makeText(this, "Silahkan isi form terlebih dahulu", Toast.LENGTH_SHORT).show()

            } else if(mRegUsername.isNotEmpty() && mRegPassword.isNotEmpty()) {

                // Login success
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {

                // Login failed
                Toast.makeText(this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

private fun EditText.isEmpty(): Boolean {
    return this.text.toString().trim().isEmpty()
}

private fun EditText.isNotEmpty(): Boolean {
    return this.text.toString().trim().isNotEmpty()
}
