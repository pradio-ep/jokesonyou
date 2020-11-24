package com.pradioep.jokesonyou.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.pradioep.jokesonyou.R
import com.pradioep.jokesonyou.ui.base.BaseActivity
import com.pradioep.jokesonyou.ui.main.MainActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(android.R.id.content).systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_splash)
        setView()
    }

    private fun setView() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },2000)
    }
}