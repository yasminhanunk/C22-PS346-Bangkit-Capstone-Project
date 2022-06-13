package com.example.antrisehat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.antrisehat.ui.activity.MainPageActivity

class splashscreenku : AppCompatActivity() {

    private val waktusplash = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreenku)


        Handler().postDelayed({
            startActivity(Intent(this, MainPageActivity::class.java))
            finish()
        }, waktusplash)
    }
}