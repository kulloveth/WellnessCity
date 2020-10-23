package com.wellnesscity.health.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentOnboardingBinding
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
                R.raw.exercise
            ),
            IntroSlide(
                "Diet Tips / Advice",
                "Find out basics of health diet and good nutrition, Start eating well and keep a balanced diet",
                R.raw.diet
            ),
            IntroSlide(
                "Covid 19 Symptoms/Prevention tips",
                "Get regular Reminders of Covid-19 prevention tips ensuring you stay safe",
                R.raw.covid19
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LayoutParams =
            LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsConta
            iner.addView(indicators[i])
        }

    }
}