package com.example.pavel.archexample.flow.launch

import org.koin.dsl.context.ModuleDefinition

val SplashModule: ModuleDefinition.() -> Unit = {
    module("test") {
        scope("Splash") { SplashPresenter() }
    }
}