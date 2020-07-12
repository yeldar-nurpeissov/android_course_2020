package com.example.app.feature.auth.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.app.R
import com.example.app.core.DI
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    private var di: DI?=null

    private val viewModel: AuthViewModel by lazy {
        di!!.getAuthViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        di = DI()
        di?.inject(this)

        initListener()
        observeViewModel()
    }

    private fun initListener() {
        loginButton.setOnClickListener {
            val username = usernameEditText.text?.toString()
            val password = passwordEditText.text?.toString()

            viewModel.onLoginButtonClicked(
                username = username,
                password = password
            )
        }

        showTokenButton.setOnClickListener {
            viewModel.onShowTokenButtonClicked()
        }
    }

    private fun observeViewModel() {
        viewModel.authState.observe(this, Observer { state ->

            loadingProgressBar.showOrHide(state is AuthUIState.Loading)

            when(state) {
                is AuthUIState.Error -> showToast(state.throwable.message)
                is AuthUIState.ValidationError -> showToast(state.message)
                is AuthUIState.Success -> showToast("Success!!!")
            }
        })

        viewModel.tokenEvent.observe(this, Observer {
            showToast("This is access token: ${it.accessToken}")
        })
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun View.showOrHide(shouldShow: Boolean) {
    this.visibility = if (shouldShow) View.VISIBLE else View.GONE
}