package com.wellnesscity.health.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentOnboardingBinding
import com.wellnesscity.health.ui.activity.FeatureActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_onboarding.view.*


@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private var binding: FragmentOnboardingBinding? = null

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
                            requireActivity().finish()
                            requireActivity().startActivity(
                                Intent(
                                    requireActivity(),
                                    FeatureActivity::class.java
                                )
                            )
                        }
                    }else{
                        binding?.buttonNext?.text = "Next"
                        binding?.buttonNext?.setOnClickListener {

                                binding?.viewPager?.currentItem?.let {
                                    var p = it
                                binding?.viewPager?.setCurrentItem(p+1, false)
                            }
                        }
                    }
                }
            })
    }

}