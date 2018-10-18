package com.example.pavel.archexample.flow.launch.navigation

import org.koin.dsl.context.ModuleDefinition

private const val SCOPE_NAME = "navigation"
val NavigationModule: ModuleDefinition.() -> Unit = {
    module("navigation") {
        scope(SCOPE_NAME) { NavigationPresenter() }
    }
}