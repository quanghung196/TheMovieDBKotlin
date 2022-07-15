package com.example.themoviedbkotlin.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedbkotlin.R
import com.example.themoviedbkotlin.databinding.FragmentHomeBinding

import com.example.themoviedbkotlin.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutID: Int = R.layout.fragment_home

    override fun onViewReady() {
        viewModel.apply {
            getMovieList()
        }
        lifecycleScope.launchWhenStarted {
            viewModel.movies.collect { movieList ->
                Log.e("movie", movieList.toString())
            }
        }
    }
}

