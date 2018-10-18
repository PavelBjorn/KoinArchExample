package com.example.pavel.archexample.flow.launch.navigation.main

import android.support.v7.app.AppCompatActivity
import com.example.pavel.archexample.R
import com.example.pavel.archexample.flow.base.BaseFragment
import com.example.pavel.archexample.flow.launch.navigation.NavigationPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainFragment : BaseFragment<MainPresenter.MainView, MainPresenter>() {

    override val presenter: MainPresenter by inject { parametersOf(activity as AppCompatActivity, childFragmentManager) }

    private val testPresenter: NavigationPresenter by inject()

    override fun onResume() {
        super.onResume()
        testPresenter.detachView()
    }

    override fun getContenrViewLayoutRes() = R.layout.fragment_main

    override fun scopeName() = "Main"
}