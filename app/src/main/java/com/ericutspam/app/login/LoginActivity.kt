package com.ericutspam.app.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.ericutspam.app.MainActivity
import com.ericutspam.app.R
import com.ericutspam.app.dashboard.DashboardHomeActivity
import com.ericutspam.app.mysharedpreference.MySharedPreference
import com.ericutspam.app.register.RegisterActivity

class LoginActivity : ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        mysharedpreference = MySharedPreference(this)
        val toRegister : TextView = findViewById(R.id.fromlogin_toregister)
        val goBack : TextView = findViewById(R.id.fromlogin_goback)
        val loginUser : EditText = findViewById(R.id.login_user)
        val loginPass : EditText = findViewById(R.id.login_pass)
        val loginButton : Button = findViewById(R.id.login_button)
        toRegister.setOnClickListener{
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
        goBack.setOnClickListener {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        loginButton.setOnClickListener {
            if(loginUser.text.toString().isEmpty() || loginPass.text.toString().isEmpty()){
                Toast.makeText(this, "Please input username and password!",Toast.LENGTH_SHORT).show()
            }
            else{
                if(loginUser.text.toString()==mysharedpreference.getSavedUsername() && loginPass.text.toString()==mysharedpreference.getSavedPassword()){
                    Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
                    mysharedpreference.saveIslogin(true)
                    val i = Intent(this@LoginActivity, DashboardHomeActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else{
                    Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}