package com.ericutspam.app.mysharedpreference

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference(private val context: Context) {

    private val PREFS_FILENAME = "ERIC_PREFS"
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_FILENAME, 0)
    }
    fun saveCredentials(username: String, password: String, nim: Int) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putInt("nim", nim)
        editor.apply()
    }
    fun saveIslogin(logon: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("islogin", logon)
        editor.apply()
    }
    fun getSavedUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun getSavedPassword(): String? {
        return sharedPreferences.getString("password", null)
    }
    fun getSavedNim(): Int? {
        return sharedPreferences.getInt("nim", 0)
    }
    fun getIslogin(): Boolean {
        return sharedPreferences.getBoolean("islogin", false)
    }
}
