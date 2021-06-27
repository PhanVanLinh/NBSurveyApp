package com.linh.androidnbsurveyapp.ui.feature.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.linh.androidnbsurveyapp.R
import com.linh.androidnbsurveyapp.common.afterTextChanged
import com.linh.androidnbsurveyapp.common.showErrorDialog
import com.linh.androidnbsurveyapp.databinding.ActivityLoginBinding
import com.linh.androidnbsurveyapp.ui.BaseActivity
import com.linh.androidnbsurveyapp.ui.feature.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initEvents()
        observeViewModel()
    }

    private fun initEvents() {
        binding.edtEmail.afterTextChanged {
            loginViewModel.loginDataChanged(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        binding.edtPassword.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding.edtEmail.text.toString(),
                            binding.edtPassword.text.toString()
                        )
                }
                false
            }
        }

        binding.buttonLogin.setOnClickListener {
            loginViewModel.login(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }
    }

    private fun observeViewModel() {
        loginViewModel.loginFormState.asLiveData().observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            binding.buttonLogin.isEnabled = loginState.isDataValid

            if (loginState.emailError != null) {
                binding.edtEmail.error = getString(loginState.emailError)
            }
            if (loginState.passwordError != null) {
                binding.edtPassword.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResultState.asLiveData().observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.loading == true) {
                binding.buttonLogin.isEnabled = false
                binding.progressLoading.visibility = View.VISIBLE
                binding.buttonLogin.text = ""
            } else {
                binding.buttonLogin.isEnabled = true
                binding.progressLoading.visibility = View.GONE
                binding.buttonLogin.text = getString(R.string.login)
            }

            if (loginResult.error != null) {
                showErrorDialog(this@LoginActivity, getString(loginResult.error))
                return@Observer
            }

            if (loginResult.success != null) {
                moveToHomeScreen()
                return@Observer
            }
        })
    }

    private fun moveToHomeScreen() {
        finish()
        startActivity(Intent(this, HomeActivity::class.java))
    }
}