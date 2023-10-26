package com.ericutspam.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.ericutspam.app.dashboard.DashboardHomeActivity
import com.ericutspam.app.login.LoginActivity
import com.ericutspam.app.mysharedpreference.MySharedPreference
import com.ericutspam.app.register.RegisterActivity

class MainActivity : ComponentActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)
        mysharedpreference = MySharedPreference(this)
        val toLogin : Button = findViewById(R.id.goto_login)
        val toRegister : Button = findViewById(R.id.goto_register)

        toLogin.setOnClickListener {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
        toRegister.setOnClickListener {
            val i = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if(mysharedpreference.getIslogin()){
            Toast.makeText(this, "Already Login!", Toast.LENGTH_SHORT).show()
            val i = Intent(this@MainActivity, DashboardHomeActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}