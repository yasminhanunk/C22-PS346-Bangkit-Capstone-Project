package com.example.antrisehat.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.antrisehat.R
import com.example.antrisehat.data.model.UserPreference
import com.example.antrisehat.ui.viewmodel.MainViewModel
import com.example.antrisehat.ui.viewmodel.ViewModelFactory
import com.example.antrisehat.ui.viewmodel.loginrs

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class RoutingActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routing)

        setupViewModel()
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) {
            if (it.isLogin) {
                startActivity(Intent(this, MainPageActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, loginrs ::class.java))
                finish()
            }
        }
    }
}