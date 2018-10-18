package com.example.pavel.archexample.flow.launch.navigation.main

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.pavel.archexample.flow.base.BasePresenter
import com.example.pavel.archexample.flow.base.Contract

class MainPresenter(val navigator: Navigator) : BasePresenter<MainPresenter.MainView>() {
    override fun onViewAttached() {

    }

    interface MainView : Contract.BaseView
}


class Navigator(activity: FragmentActivity, fragmentManager: FragmentManager)