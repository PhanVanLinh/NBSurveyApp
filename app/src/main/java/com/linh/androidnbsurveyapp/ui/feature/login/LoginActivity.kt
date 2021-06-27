package com.linh.androidnbsurveyapp.ui.feature.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.linh.androidnbsurveyapp.common.afterTextChanged
import com.linh.androidnbsurveyapp.databinding.ActivityLoginBinding
import com.linh.androidnbsurveyapp.ui.BaseActivity
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

            binding.buttonLogin.setOnClickListener {
                binding.progressLoading.visibility = View.VISIBLE
                binding.buttonLogin.text = ""

                loginViewModel.login(
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }
        }
    }
}