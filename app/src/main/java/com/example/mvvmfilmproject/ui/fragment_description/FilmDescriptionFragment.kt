package com.example.mvvmfilmproject.ui.fragment_description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mvvmfilmproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDescriptionFragment : Fragment() {

    private val args: FilmDescriptionFragmentArgs by navArgs()
    private val viewModel by viewModels<FilmDescriptionViewModel>()
    private var like = LIKE_OFF

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate((R.layout.fragment_film_description), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFilmDescription(args.id)
        val likeButton = view.findViewById<ImageView>(R.id.ivLike)
        viewModel.favouriteLiveData.observe(viewLifecycleOwner) {
            if (viewModel.favouriteLiveData.value == LIKE_ON) {
                like = LIKE_ON
                likeButton.setImageResource(R.drawable.like_on)
            }
        }

        viewModel.liveData.observe(viewLifecycleOwner) {
            val filmDescription = viewModel.liveData.value
            val imageLink = filmDescription?.imageLink.toString()
            val image = view.findViewById<ImageView>(R.id.ivFilmImage)

            view.findViewById<TextView>(R.id.tvFilmName).text = filmDescription?.name.toString()
            view.findViewById<TextView>(R.id.tvFilmDesc).text =
                filmDescription?.description.toString()

            Glide.with(image).load(imageLink).into(image)
        }
        likeButton.setOnClickListener {
            if (like == LIKE_OFF) {
                addFavouriteFilm(likeButton, args.id)
            } else if (like == LIKE_ON) {
                deleteFavouriteFilm(likeButton, args.id)
            }
        }
    }

    private fun addFavouriteFilm(image: ImageView, id: Int) {
        viewModel.addFavouriteFilm(id)
        image.setImageResource(R.drawable.like_on)
        like = LIKE_ON
    }

    private fun deleteFavouriteFilm(image: ImageView, id: Int) {
        viewModel.deleteFavouriteFilm(id)
        image.setImageResource(R.drawable.like_off)
        like = LIKE_OFF
    }

    companion object {
        private const val LIKE_ON = true
        private const val LIKE_OFF = false
    }
}