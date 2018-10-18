package com.example.pavel.archexample.flow.launch

import android.support.v4.app.Fragment
import com.example.pavel.archexample.R
import com.example.pavel.archexample.flow.base.BaseActivity
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<SplashPresenter.SplashView, SplashPresenter>() {

    override val presenter: SplashPresenter by inject()

    override fun getContentViewLayoutRes() = R.layout.blanck_view

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
    }
}