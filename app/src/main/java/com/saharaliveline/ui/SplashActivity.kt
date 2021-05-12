package com.saharaliveline.ui

import android.content.Intent
import android.os.Bundle
import com.saharaliveline.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val activityScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        activityScope.launch {
            delay(SPLASH_DELAY)
            val intent = Intent(this@SplashActivity, MatchActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    companion object {
        private const val SPLASH_DELAY = 2000L
    }


}