package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie.databinding.FragmentMovieBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private var imageId = 0
    private var title: String? = null
    private var info: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageId = it.getInt("imageId")
            title = it.getString("title")
            info = it.getString("info")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setImageResource(imageId)
        binding.textViewTitle.text = title
        binding.textViewInfo.text = info
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic fun newInstance(imageId: Int, title: String?, info: String?) : MovieFragment =
                MovieFragment().apply {
                    arguments = Bundle().apply {
                        putInt("imageId", imageId)
                        putString("title", title)
                        putString("info", info)
                    }
                }
    }
}