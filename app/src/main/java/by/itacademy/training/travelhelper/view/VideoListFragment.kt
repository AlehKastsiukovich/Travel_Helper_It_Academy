package by.itacademy.training.travelhelper.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.training.travelhelper.databinding.FragmentVideoListBinding

class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
