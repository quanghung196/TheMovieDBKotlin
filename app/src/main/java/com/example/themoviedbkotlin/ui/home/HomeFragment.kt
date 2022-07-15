package com.example.themoviedbkotlin.ui.home

import androidx.fragment.app.viewModels
import com.example.themoviedbkotlin.R
import com.example.themoviedbkotlin.databinding.FragmentHomeBinding

import com.example.themoviedbkotlin.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutID: Int = R.layout.fragment_home

    override fun onViewReady() {

    }
}

