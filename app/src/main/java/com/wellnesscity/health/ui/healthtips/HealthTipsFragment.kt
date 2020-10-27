package com.wellnesscity.health.ui.healthtips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentHealthTipsBinding

class HealthTipsFragment : Fragment() {
    private var binding:FragmentHealthTipsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthTipsBinding.inflate(layoutInflater)
        return binding?.root
    }

}