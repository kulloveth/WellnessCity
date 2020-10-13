package com.wellnesscity.health

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.logging.Handler

class SplashScreen : AppCompatActivity() {

private val SPLASH_DELAY: Long = 1000
    private var mDelayHandler: android.os.Handler? = null
    private var progressBarStatus = 0
    var dummy: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
       mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

      private fun launchMainActivity() {
          var intent = Intent(this, MainActivity::class.java)
          intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
          startActivity(intent)
          this.finish()
          mDelayHandler!!.removeCallbacks(mRunnable)

    }

    private val mRunnable: Runnable = Runnable {
        Thread(Runnable {
            while (progressBarStatus < 100) {
                try {
                    dummy = dummy + 25
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                progressBarStatus = dummy
                progress_bar.progress = progressBarStatus }
            launchMainActivity()
                }).start()
            }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}