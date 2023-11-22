package com.example.sharedpreferences_khoapham

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: AppCompatButton
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var cbRememberInfo: CheckBox
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // khai báo sharedPreferences
        sharedPreferences = getSharedPreferences("app-cache", Context.MODE_PRIVATE)

        btnLogin = findViewById(R.id.btn_login)
        edtEmail = findViewById(R.id.edit_text_email)
        edtPassword = findViewById(R.id.edit_text_password)
        cbRememberInfo = findViewById(R.id.checkbox_remember_info)

        // lấy thông tin từ sharedPreferences
        val isChecked = sharedPreferences.getBoolean("isChecked", false)
        if (isChecked) {
            val email = sharedPreferences.getString("email", "")
            val password = sharedPreferences.getString("password", "")

            edtEmail.setText(email)
            edtPassword.setText(password)
            cbRememberInfo.isChecked = true
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if(email == "tpnguyen04@gmail.com" && password == "123456781") {
                sharedPreferences.edit {
                    if(cbRememberInfo.isChecked) {
                        putString("email", email)
                        putString("password", password)
                        putBoolean("isChecked", true)
                    } else {
                        clear()
                    }
                }
            } else {
                Toast.makeText(this@MainActivity, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}