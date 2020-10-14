package com.wellnesscity.health

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.onboarding_screen.*

class OnboardingActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
            listOf(
                IntroSlide(
                        "Health Tips / Advice",
                        "Discover tips and advice to help you to help maintain transform and main your health",
                        R.drawable.ic_bro
                ),
                IntroSlide(
                        "Diet Tips / Advice",
                        "Find out basics of health diet and good nutrition, Start eating well and keep a balanced diet",
                        R.drawable.ic_amico
                ),
                IntroSlide(
                        "Covid 19 Symptoms/Prevention tips",
                        "Get regular Reminders of Covid19 prevention tips ensuring you stay safe",
                        R.drawable.ic_pana
                )
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
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
            indicatorsContainer.addView(indicators[i])
        }

    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                              applicationContext,
                              R.drawable.indicator_active
                        )
                )
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.indicator_inactive
                        )
                )
            }
        }

    }

}