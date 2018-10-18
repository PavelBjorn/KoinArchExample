package com.example.pavel.archexample.flow.launch.navigation

import android.content.Context
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import kotlinx.android.synthetic.main.activity_navigation.view.*

class NavigationLayout(context: Context, attrs: AttributeSet?) : DrawerLayout(context, attrs), NavigationPresenter.NavigationView {

    override fun onFinishInflate() {
        super.onFinishInflate()
        (context as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.title = "TEST"
        }
    }
}