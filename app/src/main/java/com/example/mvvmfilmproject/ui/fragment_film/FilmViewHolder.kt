package com.example.mvvmfilmproject.ui.fragment_film

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmfilmproject.R
import com.example.mvvmfilmproject.domain.FilmRecyclerData

class FilmViewHolder(itemView: View, private val onClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    fun onBind(item: FilmRecyclerData) {
        val number = itemView.findViewById<TextView>(R.id.tvNumber)
        val image = itemView.findViewById<ImageView>(R.id.ivPosterImg)
        val name = itemView.findViewById<TextView>(R.id.tvFilmName)
        val rating = itemView.findViewById<TextView>(R.id.tvRating)
        val oskarImage = itemView.findViewById<ImageView>(R.id.ivIsOskar)
        val film = itemView.findViewById<View>(R.id.rvFilm)
        number.text = item.id.toString()
        Glide.with(image).load(item.imageLink).into(image)
        name.text = item.name
        rating.text = "${item.rating}/10"
        oskarImage.isVisible = item.isOskar
        film.setOnClickListener { onClick(item.id) }
    }
}
