package com.example.newedutrax.ui.activity.verify

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.newedutrax.R
import com.example.newedutrax.databinding.ActivityVerifyBinding
import com.example.newedutrax.ui.activity.main.MainActivity
import com.example.newedutrax.ui.activity.start_course.StartCourseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VerifyActivity : AppCompatActivity(), NavigatorVer {
    private lateinit var binding: ActivityVerifyBinding
    private lateinit var viewModel: VerifyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[VerifyViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify)
        binding.vm = viewModel
        binding.btnVerify.setOnClickListener {
            intent.getStringExtra("email")?.let { it1 -> viewModel.verify(it1) }
            val intent = Intent(binding.root.context, MainActivity::class.java)
            startActivity(intent)

        }

        checkState()

    }

    private fun checkState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                if (it.massage?.isNotEmpty() == true) Toast.makeText(
                    binding.root.context,
                    it.massage.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun goHome() {
        val intent = Intent(binding.root.context, MainActivity::class.java)
        startActivity(intent)    }

    override fun showToast(massage: String) {
        Toast.makeText(
            binding.root.context,
            massage,
            Toast.LENGTH_SHORT
        ).show()
    }
}