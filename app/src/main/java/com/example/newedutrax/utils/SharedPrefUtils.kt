package com.example.newedutrax.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.newedutrax.api.models.LogResponse

object SharedPrefUtils {
    private val ID = "ID"
    private val NAME = "NAME"
    private val EMAIL = "EMAIL"
    private val TOKEN = "TOKEN"
    private var mPrefs: SharedPreferences? = null


    private fun getSharedPref(context: Context): SharedPreferences {
        if (mPrefs == null) mPrefs = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
        return mPrefs!!
    }

    private fun getSharedPrefEditor(context: Context): SharedPreferences.Editor =
        getSharedPref(context).edit()

    fun saveUserData(context: Context, item: LogResponse?) {
        val editor = getSharedPrefEditor(context)
        editor.putString(ID, item?._id)
        editor.putString(NAME, item?.name)
        editor.putString(EMAIL, item?.email)
        editor.putString(TOKEN, item?.token)
        editor.apply()
    }

    fun getToken(context: Context): String = getSharedPref(context).getString(TOKEN, "").toString()

    fun isUserLogged(context: Context): Boolean = getToken(context).isNotEmpty()

    fun clearUserData(context: Context) {
        val editor = getSharedPrefEditor(context)
        editor.remove(ID)
        editor.remove(TOKEN)
        editor.remove(EMAIL)
        editor.remove(NAME)
        editor.apply()
    }
}