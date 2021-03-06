package com.wellnesscity.health.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private var binding: FragmentOnboardingBinding? = null
    private val viewModel:OnboardingViewModel by viewModels()

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Health Tips / Advice",
                "Discover tips and advice to help you to help maintain transform and main your health",
                "exercise.json"
            ),
            IntroSlide(
                "Diet Tips / Advice",
                "Find out basics of health diet and good nutrition, Start eating well and keep a balanced diet",
                "diet.json"
            ),
            IntroSlide(
                "Covid 19 Symptoms/Prevention tips",
                "Get regular Reminders of Covid-19 prevention tips ensuring you stay safe",
                "covid19.json"
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewPager?.adapter = introSliderAdapter
        binding?.indicator?.setViewPager(binding?.viewPager)
        binding?.viewPager?.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == introSliderAdapter.itemCount - 1) {
                        val animation = AnimationUtils.loadAnimation(
                            requireActivity(),
                            R.anim.app_name_animation
                        )
                        binding?.buttonNext?.animation = animation
                        binding?.buttonNext?.text = "Finish"
                        binding?.buttonNext?.setOnClickListener {
                                viewModel.saveOnboarding(true)
                            requireView().findNavController()
                                .navigate(OnboardingFragmentDirections.actionOnboardingFragmentToWelcomeFragment())
                        }
                    } else {
                        binding?.buttonNext?.text = "Next"
                        binding?.buttonNext?.setOnClickListener {
                            binding?.viewPager?.currentItem?.let {
                                binding?.viewPager?.setCurrentItem(it + 1, false)
                            }
                        }
                    }
                }
            })
    }



}