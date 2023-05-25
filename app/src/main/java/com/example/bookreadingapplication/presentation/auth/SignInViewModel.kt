package com.example.bookreadingapplication.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreadingapplication.core.GlobalData
import com.example.bookreadingapplication.data.api.ApiService
import com.example.bookreadingapplication.data.api.AuthService
import com.example.bookreadingapplication.data.model.User
import com.example.bookreadingapplication.data.request.SignInRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.bookreadingapplication.data.model.ApiResult

class SignInViewModel : ViewModel() {
    private val authService: AuthService = ApiService.getAuthService()

    private val _signInResult = MutableStateFlow<ApiResult<User>>(ApiResult.Loading)
    val signInResult: StateFlow<ApiResult<User>> get() = _signInResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _signInResult.value = ApiResult.Loading
            try {
                val response = authService.signIn(SignInRequest(email, password))
                val user = response.data
                val success = response.error == null && user != null
                if (success) {
                    _signInResult.value = ApiResult.Success(user ?: User())
                    ApiService.token = response.token ?: "Null token"
                    GlobalData.user = user ?: User()
                } else {
                    val errorBody = response.error
                    _signInResult.value = ApiResult.Error(Exception(errorBody))
                }
            } catch (e: Exception) {
                _signInResult.value = ApiResult.Error(e)
            }
        }
    }
}