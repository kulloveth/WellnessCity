package com.wellnesscity.health.ui.illness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wellnesscity.health.databinding.FragmentIllnessBinding


class IllnessFragment : Fragment() {
    private var binding: FragmentIllnessBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIllnessBinding.inflate(layoutInflater)
        return binding?.root
    }


}