package com.example.antrisehat.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.antrisehat.R

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)
    }

    companion object {
        const val EXTRA_STORY = "story"
    }

}