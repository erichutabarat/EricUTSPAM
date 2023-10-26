package com.ericutspam.app.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.ericutspam.app.MainActivity
import com.ericutspam.app.R
import com.ericutspam.app.login.LoginActivity
import com.ericutspam.app.mysharedpreference.MySharedPreference

class RegisterActivity : ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        mysharedpreference = MySharedPreference(this)
        val toLogin : TextView = findViewById(R.id.fromregister_tologin)
        val goBack : TextView = findViewById(R.id.fromregister_goback)
        val registerUser : EditText = findViewById(R.id.register_user)
        val registerPass : EditText = findViewById(R.id.register_pass)
        val registerNim : EditText = findViewById(R.id.register_nim)
        val registerButton : Button = findViewById(R.id.register_button)
        toLogin.setOnClickListener {
            val i = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
        goBack.setOnClickListener {
            val i = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        registerButton.setOnClickListener {
            if(registerUser.text.toString().isEmpty() || registerPass.text.toString().isEmpty() || registerNim.text.toString().isEmpty()){
                Toast.makeText(this, "Please input username and password", Toast.LENGTH_SHORT).show()
            }
            else{
                try{
                    val registeredNim = registerNim.text.toString().toInt()
                    mysharedpreference.saveCredentials(registerUser.text.toString(), registerPass.text.toString(), registeredNim)
                    Toast.makeText(this, "Successfully registered!", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }catch (e: NumberFormatException){
                    Toast.makeText(this, "NIM MUST BE Integer", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}