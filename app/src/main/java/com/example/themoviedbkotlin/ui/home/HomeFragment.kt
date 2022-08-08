package com.example.themoviedbkotlin.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.themoviedbkotlin.R
import com.example.themoviedbkotlin.databinding.FragmentHomeBinding

import com.example.themoviedbkotlin.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutID: Int = R.layout.fragment_home

    private val movieAdapter = HomeAdapter(itemClickListener = { movie ->
        showToast(message = movie.id)
    })

    override fun onViewReady() {
        viewModel.apply {
            getMovieList()
        }

        binding.recyclerMovie.let {
            if (it.adapter == null) {
                it.adapter = movieAdapter
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collectLatest { movieList ->
                    movieAdapter.submitList(movieList)
                }
            }
        }
    }
}

