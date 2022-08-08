package com.example.themoviedbkotlin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbkotlin.BR
import com.example.themoviedbkotlin.R
import com.example.themoviedbkotlin.utils.dismissLLoadingDialog
import com.example.themoviedbkotlin.utils.showDialog
import com.example.themoviedbkotlin.utils.showLoadingDialog
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    protected lateinit var binding: VB

    protected abstract val viewModel: VM

    @get:LayoutRes
    protected abstract val layoutID: Int

    abstract fun onViewReady()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
        return binding.root
    }


    private fun observerEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.apply {
                launch {
                    isLoading.collect {
                        handleLoading(isLoading = it)
                    }
                }
                launch {
                    errorMessage.collect {
                        handleErrorMessage(message = it)
                    }
                }
                launch {
                    noInternetConnectionEvent.collect {
                        handleErrorMessage(message = getString(R.string.no_internet_connection))
                    }
                }
                launch {
                    connectTimeoutEvent.collect {
                        handleErrorMessage(message = getString(R.string.connect_timeout))
                    }
                }
                launch {
                    forceUpdateAppEvent.collect {
                        handleErrorMessage(message = getString(R.string.force_update_app))
                    }
                }
                launch {
                    serverMaintainEvent.collect {
                        handleErrorMessage(message = getString(R.string.server_maintain_message))
                    }
                }
                launch {
                    unknownErrorEvent.collect {
                        handleErrorMessage(message = getString(R.string.unknown_error))
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewReady()
        observerEvents()
    }

    protected fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * override this if not use loading dialog (example progress bar)
     */
    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLLoadingDialog()
    }

    private fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        dismissLLoadingDialog()
        showDialog(
            message = message,
            textPositive = getString(R.string.ok)
        )
    }
}