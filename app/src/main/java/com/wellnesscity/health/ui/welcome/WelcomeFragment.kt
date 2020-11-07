package com.wellnesscity.health.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentWelcomeBinding
import com.wellnesscity.health.util.tint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeDrawableCOlor()
    }


    fun changeDrawableCOlor() {
        ResourcesCompat.getDrawable(resources, R.drawable.menu_bg, resources.newTheme())
            ?.tint(requireContext(), R.color.menuOne0)
        binding?.healthTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
        ResourcesCompat.getDrawable(resources, R.drawable.menu_bg, resources.newTheme())
            ?.tint(requireContext(), R.color.menuTwo)
        binding?.dietTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
        ResourcesCompat.getDrawable(resources, R.drawable.menu_bg, resources.newTheme())
            ?.tint(requireContext(), R.color.menuThree)
        binding?.illnessTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
        ResourcesCompat.getDrawable(resources, R.drawable.menu_bg, resources.newTheme())
            ?.tint(requireContext(), R.color.menuFour)
        binding?.covidTv?.setBackgroundDrawable(resources.getDrawable(R.drawable.menu_bg))
    }
}