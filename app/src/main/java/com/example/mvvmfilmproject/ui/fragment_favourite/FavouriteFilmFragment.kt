package com.example.mvvmfilmproject.ui.fragment_favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmfilmproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFilmFragment : Fragment() {

    private val viewModel by viewModels<FavouriteFilmViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.rvFavoriteFilm)
        super.onViewCreated(view, savedInstanceState)
        val onClick: (Int) -> Unit = {
            val action =
                FavouriteFilmFragmentDirections.actionFavouriteFilmFragmentToFilmDescriptionFragment(
                    it)
            findNavController().navigate(action)
        }

        viewModel.getFavoriteFilms()
        viewModel.liveData.observe(viewLifecycleOwner) { it ->
            val films = viewModel.liveData.value
            val adapter = films?.let { FavouriteFilmAdapter(it, onClick) }

            if (it.isEmpty()) {
                view.findViewById<TextView>(R.id.tvFavoriteFilm).text =
                    resources.getText(R.string.empty)
            }
            recycler?.adapter = adapter
            recycler?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            val isVisibleProgressBar = viewModel.progressLiveData.value
            if (isVisibleProgressBar == true) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        val btnNavigateToFilmFragment = view.findViewById<ImageView>(R.id.bntToFilmFragment)

        btnNavigateToFilmFragment.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}