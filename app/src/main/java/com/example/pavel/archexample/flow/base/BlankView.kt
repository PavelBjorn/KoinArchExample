package com.example.pavel.archexample.flow.base

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.pavel.archexample.flow.launch.SplashPresenter
import com.example.pavel.archexample.flow.launch.navigation.NavigationActivity

class BlankView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), SplashPresenter.SplashView {
    override fun closeSplash() {
        context.startActivity(Intent(context, NavigationActivity::class.java))
    }
}