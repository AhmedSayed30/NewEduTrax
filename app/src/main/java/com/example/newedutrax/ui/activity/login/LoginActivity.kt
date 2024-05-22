package com.example.newedutrax.ui.activity.login

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newedutrax.ui.activity.main.MainActivity
import com.example.newedutrax.R
import com.example.newedutrax.api.models.LogResponse
import com.example.newedutrax.databinding.ActivityLoginBinding
import com.example.newedutrax.ui.activity.register.RegisterActivity
import com.example.newedutrax.utils.SharedPrefUtils.saveUserData

class LoginActivity : AppCompatActivity(), NavigatorLog {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = viewModel
        viewModel.navigator = this
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

        }

    }

    var alertDialog: AlertDialog? = null
    var progressDialog: ProgressDialog? = null

    override fun showlading(massage: String) {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(massage)
        progressDialog?.show()
    }

    override fun hideDialoge() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    override fun showMassage(massage: String) {
        alertDialog =
            AlertDialog.Builder(this)
                .setMessage(massage)
                .setPositiveButton("Ok") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }.show()
    }

    override fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun bacK(item: LogResponse?) {
        saveUserData(binding.root.context, item)
    }

    override fun showToast(massage: String) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
    }
}