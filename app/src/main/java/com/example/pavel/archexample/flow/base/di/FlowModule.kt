package com.example.pavel.archexample.flow.base.di

import com.example.pavel.archexample.flow.launch.SplashModule
import com.example.pavel.archexample.flow.launch.navigation.NavigationModule
import com.example.pavel.archexample.flow.launch.navigation.main.mainModule
import org.koin.dsl.module.module

val flowModule = module {
    SplashModule(this)
    NavigationModule(this)
    mainModule(this)
}