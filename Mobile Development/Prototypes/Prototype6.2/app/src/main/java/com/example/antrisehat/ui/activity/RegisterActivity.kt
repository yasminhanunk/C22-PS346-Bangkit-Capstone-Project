package com.example.antrisehat.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.antrisehat.R
import com.example.antrisehat.databinding.ActivityRegisterBinding
import com.example.antrisehat.ui.viewmodel.LoginrsViewModel
import com.example.antrisehat.ui.viewmodel.RegisterViewModel
import com.example.antrisehat.ui.viewmodel.ViewModelFactory
import com.example.antrisehat.helper.Helper
import com.example.antrisehat.ui.viewmodel.loginrs


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMyButtonEnable()
        editTextListener()
        buttonListener()
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

        binding.signIn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, loginrs::class.java))
            finish()
        }
    }

    private fun setMyButtonEnable() {
        binding.btnregister.isEnabled =
            binding.emailuser.text.toString().isNotEmpty() &&
                    binding.pwuser.text.toString().isNotEmpty() &&
                    binding.pwuser.text.toString().length >= 6 &&
                    Helper.isEmailValid(binding.emailuser.text.toString())
    }

    private fun buttonListener() {
        binding.btnregister.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.emailuser.text.toString()
            val password = binding.pwuser.text.toString()

            registerViewModel.register(name, email, password, object : Helper.ApiCallbackString {
                override fun onResponse(success: Boolean, message: String) {
                    showAlertDialog(success, message)
                }
            })
        }
        binding.logosetting.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }

    private fun showAlertDialog(param: Boolean, message: String) {
        if (param) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.information))
                setMessage(getString(R.string.register_success))
                setPositiveButton(getString(R.string.continue_)) { _, _ ->
                    val intent = Intent(context, loginrs::class.java)
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
                setMessage(getString(R.string.register_failed)+", $message")
                create()
                show()
            }
        }
    }

}