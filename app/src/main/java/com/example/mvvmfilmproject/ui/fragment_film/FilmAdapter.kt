package com.example.mvvmfilmproject.ui.fragment_film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmfilmproject.R
import com.example.mvvmfilmproject.domain.FilmRecyclerData

class FilmAdapter(
    private val listFilmData: List<FilmRecyclerData>,
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_film, parent, false)
        return FilmViewHolder(view, onClick)
    }

    override fun getItemCount(): Int = listFilmData.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.onBind(listFilmData[position])
    }
}