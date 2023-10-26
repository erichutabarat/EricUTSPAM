package com.ericutspam.app.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ericutspam.app.mysharedpreference.MySharedPreference
import com.ericutspam.app.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardHomeActivity : AppCompatActivity() {
    private lateinit var mysharedpreference : MySharedPreference
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_home)
        loadfragment(DashboardHomeFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    loadfragment(DashboardHomeFragment())
                    true
                }
                R.id.profile -> {
                    loadfragment(DashboardProfileFragment())
                    true
                }
                else -> false
            }
        }
    }
    private fun loadfragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}