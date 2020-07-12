package com.example.app.feature.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.core.Resource
import com.example.app.feature.auth.domain.AuthUseCase
import com.example.app.feature.auth.domain.GetTokenUseCase

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val getTokenUseCase: GetTokenUseCase
) {

    val authState: LiveData<AuthUIState> get() = _authState
    val tokenEvent: LiveData<TokenEvent> get() = _tokenEvent

    private val _authState = MutableLiveData<AuthUIState>()
    private val _tokenEvent = MutableLiveData<TokenEvent>()

    fun onLoginButtonClicked(
        username: String?,
        password: String?
    ) {
        if (username.isNullOrBlank()) {
            _authState.value = AuthUIState.ValidationError("Fill username")
            return
        }

        if (password.isNullOrBlank()) {
            _authState.value = AuthUIState.ValidationError("Enter password")
            return
        }

        if (password.length < 4) {
            _authState.value = AuthUIState.ValidationError("Enter password more than 4 char")
            return
        }

        _authState.value = AuthUIState.Loading

        when (val resource = authUseCase.execute(username, password)) {
            is Resource.Error ->
                _authState.value = AuthUIState.Error(resource.throwable)
            is Resource.Success ->
                _authState.value = AuthUIState.Success
        }
    }

    fun onShowTokenButtonClicked() {
        _tokenEvent.value = TokenEvent(
            accessToken = getTokenUseCase.execute()
        )
    }
}

sealed class AuthUIState {
    class Error(val throwable: Throwable) : AuthUIState()
    class ValidationError(val message: String) : AuthUIState()
    object Success : AuthUIState()
    object Loading : AuthUIState()
}

class TokenEvent(val accessToken: String?)


//" Nick     ".trim() // "Nick"
//"".isEmpty() // true
//"".isBlank() // true
//"   ".isEmpty() // false
//"   ".isBlank() // true


//