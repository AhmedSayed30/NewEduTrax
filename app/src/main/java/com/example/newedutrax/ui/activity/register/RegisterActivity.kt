package com.example.newedutrax.ui.activity.register

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newedutrax.R
import com.example.newedutrax.api.models.AuthResponse

import com.example.newedutrax.databinding.ActivityRegisterBinding
import com.example.newedutrax.ui.activity.login.LoginActivity

class RegisterActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.vm = viewModel
        binding.tvLog.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewModel.navigator = this

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
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun bacK(item: AuthResponse?) {

    }

    override fun showToast(massage: String) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
    }
}

private const val TAG = "RegisterActivity"