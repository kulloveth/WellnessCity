package com.wellnesscity.health.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wellnesscity.health.R
import com.wellnesscity.health.data.FirebaseService
import com.wellnesscity.health.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var binding: FragmentSplashBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseService(requireContext())
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding?.root
    }

}