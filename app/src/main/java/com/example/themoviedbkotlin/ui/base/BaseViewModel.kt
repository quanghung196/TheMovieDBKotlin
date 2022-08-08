package com.example.themoviedbkotlin.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbkotlin.data.remote.toBaseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    // loading flag
    private val _isLoading by lazy { MutableStateFlow(false) }
    val isLoading = _isLoading.asStateFlow()

    // error message
    private val _errorMessage by lazy { MutableSharedFlow<String>() }
    val errorMessage = _errorMessage.asSharedFlow()

    // optional flags
    private val _noInternetConnectionEvent by lazy { MutableSharedFlow<Unit>() }
    val noInternetConnectionEvent = _noInternetConnectionEvent.asSharedFlow()

    private val _connectTimeoutEvent by lazy { MutableSharedFlow<Unit>() }
    val connectTimeoutEvent = _connectTimeoutEvent.asSharedFlow()

    private val _forceUpdateAppEvent by lazy { MutableSharedFlow<Unit>() }
    val forceUpdateAppEvent = _forceUpdateAppEvent.asSharedFlow()

    private val _serverMaintainEvent by lazy { MutableSharedFlow<Unit>() }
    val serverMaintainEvent = _serverMaintainEvent.asSharedFlow()

    private val _unknownErrorEvent by lazy { MutableSharedFlow<Unit>() }
    val unknownErrorEvent = _unknownErrorEvent.asSharedFlow()

    // exception handler for coroutine
    private val exceptionHandler by lazy {
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                onError(throwable)
            }
        }
    }
    protected val viewModelScopeExceptionHandler by lazy { viewModelScope + exceptionHandler }

    /**
     * handle throwable when load fail
     */
    private suspend fun onError(throwable: Throwable) {
        when (throwable) {
            // case no internet connection
            is UnknownHostException,
            is ConnectException -> {
                _noInternetConnectionEvent.emit(Unit)
            }
            // case request time out
            is SocketTimeoutException -> {
                _connectTimeoutEvent.emit(Unit)
            }
            else -> {
                // convert throwable to base exception to get error information
                val baseException = throwable.toBaseException()
                when (baseException.httpCode) {
                    HttpURLConnection.HTTP_UNAUTHORIZED,
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                        baseException.message?.let { _errorMessage.emit(it) }
                    }
                    else -> {
                        _unknownErrorEvent.emit(Unit)
                    }
                }
            }
        }
        hideLoading()
    }

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }
}
