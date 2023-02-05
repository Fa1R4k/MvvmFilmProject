package com.example.mvvmfilmproject.ui.fragment_film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmfilmproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    private val viewModel by viewModels<FilmFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onClick: (Int) -> Unit = {
            val action = FilmFragmentDirections.actionFilmFragmentToFilmDescriptionFragment(it)
            findNavController().navigate(action)
        }
        viewModel.getFilms()
        viewModel.liveData.observe(viewLifecycleOwner) {
            val films = viewModel.liveData.value
            val adapter = films?.let { FilmAdapter(it, onClick) }
            val recycler = view.findViewById<RecyclerView>(R.id.rvFilmList)
            recycler?.adapter = adapter
            recycler?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}