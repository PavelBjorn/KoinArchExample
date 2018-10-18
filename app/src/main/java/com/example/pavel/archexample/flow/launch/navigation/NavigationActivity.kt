package com.example.pavel.archexample.flow.launch.navigation

import android.os.Bundle
import com.example.pavel.archexample.R
import com.example.pavel.archexample.flow.base.BaseActivity
import com.example.pavel.archexample.flow.launch.navigation.main.MainFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class NavigationActivity : BaseActivity<NavigationPresenter.NavigationView, NavigationPresenter>() {

    override val presenter: NavigationPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(navContainer.id, MainFragment())
                .commit()
    }

    override fun scopeName() = "navigation"

    override fun getContentViewLayoutRes() = R.layout.activity_navigation
}
