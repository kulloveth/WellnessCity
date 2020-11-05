package com.wellnesscity.health.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.wellnesscity.health.R
import com.wellnesscity.health.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private var binding: FragmentOnboardingBinding? = null
    @Inject
    lateinit var prefs: DataStore<Preferences>

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
                            lifecycleScope.launch {
                                saveOnboarding()
                            }
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

    //saves the onboarding to the preference datastore
    suspend fun saveOnboarding() {
        prefs.edit {
            val oneTime = true
            it[preferencesKey<Boolean>("onBoard")] = oneTime
        }
    }

}