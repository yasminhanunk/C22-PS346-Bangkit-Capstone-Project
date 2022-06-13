package com.example.antrisehat.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.antrisehat.R
import com.example.antrisehat.data.model.UserPreference
import com.example.antrisehat.databinding.ActivityLoginrsBinding
import com.example.antrisehat.helper.Helper
import com.example.antrisehat.ui.activity.MainPageActivity
import com.example.antrisehat.ui.activity.RegisterActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class loginrs : AppCompatActivity() {
    private lateinit var loginrsViewModel: LoginrsViewModel
    private lateinit var binding: ActivityLoginrsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginrsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewModel()
        setMyButtonEnable()
        editTextListener()
        buttonListener()

    }

    private fun setupViewModel() {
        loginrsViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginrsViewModel::class.java]
    }

    private fun editTextListener() {
        binding.emailuser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        binding.pwuser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        binding.btnlogin.setOnClickListener {
            startActivity(Intent(this@loginrs, RegisterActivity::class.java))
            finish()
        }
        binding.etSignIn.setOnClickListener {
            startActivity(Intent(this@loginrs, RegisterActivity::class.java))
        }
    }

    private fun setMyButtonEnable() {
        val resultPass = binding.pwuser.text
        val resultEmail = binding.emailuser.text

        binding.btnlogin.isEnabled = resultPass != null && resultEmail != null &&
                binding.pwuser.text.toString().length >= 6 &&
                Helper.isEmailValid(binding.emailuser.text.toString())
    }

    private fun showAlertDialog(param: Boolean, message: String) {
        if (param) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.information))
                setMessage(getString(R.string.sign_in_success))
                setPositiveButton(getString(R.string.continue_)) { _, _ ->
                    val intent = Intent(context, MainPageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        } else {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.information))
                setMessage(getString(R.string.sign_in_failed) +", $message")
                create()
                show()

            }
        }
    }

    private fun buttonListener() {
        binding.btnlogin.setOnClickListener {
            val email = binding.emailuser.text.toString()
            val pass = binding.pwuser.text.toString()

            loginrsViewModel.login(email, pass, object : Helper.ApiCallbackString {
                override fun onResponse(success: Boolean,message: String) {
                    showAlertDialog(success, message)
                }
            })
        }

        binding.logosetting?.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }
}