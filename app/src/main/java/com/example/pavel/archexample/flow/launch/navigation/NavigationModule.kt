package com.example.pavel.archexample.flow.launch.navigation

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.context.ModuleDefinition

val NavigationModule: ModuleDefinition.() -> Unit = {
    module("navigation") {
        factory { NavigationPresenter() }
        viewModel { NavigationViewModel() }
    }
}