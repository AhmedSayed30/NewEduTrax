package com.example.newedutrax.ui.activity.splash


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.newedutrax.R
import com.example.newedutrax.ui.activity.login.LoginActivity
import com.example.newedutrax.ui.activity.main.MainActivity
import com.example.newedutrax.utils.SharedPrefUtils.isUserLogged

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            navigate()
            finish()
        }, 2000)
    }

    private fun navigate() {
        val isLogged = isUserLogged(this@SplashActivity)
        if (isLogged) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}