package com.alliebe.mastersejin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val time: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.supportActionBar?.hide()
        Handler().postDelayed({
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        },time)
    }
}