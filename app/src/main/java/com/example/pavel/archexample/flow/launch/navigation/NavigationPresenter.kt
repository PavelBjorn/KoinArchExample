package com.example.pavel.archexample.flow.launch.navigation

import com.example.pavel.archexample.flow.base.BasePresenter
import com.example.pavel.archexample.flow.base.Contract

class NavigationPresenter : BasePresenter<NavigationPresenter.NavigationView>() {

    override fun onViewAttached() {

    }

    interface NavigationView : Contract.BaseView {

    }
}