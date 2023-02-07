package com.example.mvvmfilmproject.ui.fragment_film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmfilmproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmFragment : Fragment() {
    private val viewModel by viewModels<FilmFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onClick: (Int) -> Unit = {
            val action = FilmFragmentDirections.actionFilmFragmentToFilmDescriptionFragment(it)
            findNavController().navigate(action)
        }
        viewModel.getFilms()
        val recycler = view.findViewById<RecyclerView>(R.id.rvFilmList)
        viewModel.errorLiveData.observe(viewLifecycleOwner) { res ->
            Toast.makeText(requireContext(), getString(res), Toast.LENGTH_SHORT).show()
        }
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            val isVisibleProgressBar = viewModel.progressLiveData.value
            if (isVisibleProgressBar == true) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        }
        viewModel.liveData.observe(viewLifecycleOwner) {
            val films = viewModel.liveData.value
            val adapter = films?.let { FilmAdapter(it, onClick) }
            recycler?.adapter = adapter
            recycler?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val btnNavigateToFavoriteList = view.findViewById<ImageView>(R.id.btnToFavoriteFilms)
        btnNavigateToFavoriteList.setOnClickListener {
            val action = FilmFragmentDirections.actionFilmFragmentToFavouriteFilmFragment()
            findNavController().navigate(action)
        }
    }
}