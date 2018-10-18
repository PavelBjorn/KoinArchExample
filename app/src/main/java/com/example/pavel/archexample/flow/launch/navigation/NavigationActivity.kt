package com.example.pavel.archexample.flow.launch.navigation

import android.os.Bundle
import com.example.pavel.archexample.R
import com.example.pavel.archexample.flow.base.BaseActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class NavigationActivity : BaseActivity<NavigationPresenter.NavigationView, NavigationPresenter>() {

    override val presenter: NavigationPresenter by inject()

    private val model: NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.test.observe {
            test.text = it
        }
    }

    override fun getContentViewLayoutRes() = R.layout.activity_navigation

    override fun onResume() {
        super.onResume()
        if (model.test.value.isNullOrBlank()) {
            model.test.value = "test"
        }
    }
}
