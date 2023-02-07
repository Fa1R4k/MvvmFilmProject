package com.example.mvvmfilmproject.ui.fragment_favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmfilmproject.R
import com.example.mvvmfilmproject.domain.FilmRecyclerData

class FavouriteFilmAdapter(
    private val listFilmData: List<FilmRecyclerData>,
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<FavoriteFilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteFilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_film, parent, false)
        return FavoriteFilmViewHolder(view, onClick)
    }

    override fun getItemCount(): Int = listFilmData.size

    override fun onBindViewHolder(holder: FavoriteFilmViewHolder, position: Int) {
        holder.onBind(listFilmData[position])
    }
}