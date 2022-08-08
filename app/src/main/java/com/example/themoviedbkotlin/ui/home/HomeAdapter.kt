package com.example.themoviedbkotlin.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.themoviedbkotlin.R
import com.example.themoviedbkotlin.databinding.ItemMovieBinding
import com.example.themoviedbkotlin.domain.entity.MovieEntity
import com.example.themoviedbkotlin.ui.base.BaseListAdapter

class HomeAdapter(val itemClickListener: (MovieEntity) -> Unit = {}) :
    BaseListAdapter<MovieEntity, ItemMovieBinding>(
        object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie

    override fun bindFirstTime(binding: ItemMovieBinding) {
        binding.apply {
            root.setOnClickListener {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
    }
}
