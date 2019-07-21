package com.srm.crb.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.srm.crb.AppController
import com.srm.crb.R

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password)
        title = resources.getString(R.string.reset_password)

        val userName = intent.getStringExtra("userName")


        val loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory(AppController.instance.loginRepository))
                .get(LoginViewModel::class.java)

        val password = findViewById<EditText>(R.id.et_password)
        val confirmPassword = findViewById<EditText>(R.id.et_confirm_pass)
        val proceed = findViewById<TextView>(R.id.proceed)

        proceed.setOnClickListener {
            val pass = password.text.toString().trim()
            val confPass = confirmPassword.text.toString().trim()
            if (pass.isNotEmpty() && confPass.isNotEmpty()) {
                if (pass == confPass) {
                    val details = loginViewModel.loginRepository.dataSource.getUser(userName)!!
                    loginViewModel.resetPassword(details, pass)

                    startActivity(Intent(this, BookingActivity::class.java))
                }
            } else {
                Toast.makeText(this, R.string.password_doesnt_match, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}