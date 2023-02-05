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
        viewModel.liveData.observe(viewLifecycleOwner) {
            val filmDescription = viewModel.liveData.value
            val imageLink = filmDescription?.imageLink.toString()
            val image = view.findViewById<ImageView>(R.id.ivFilmImage)
            view.findViewById<TextView>(R.id.tvFilmName).text = filmDescription?.name.toString()
            view.findViewById<TextView>(R.id.tvFilmDesc).text = filmDescription?.description.toString()

            Glide.with(image).load(imageLink).into(image)
        }
    }
}