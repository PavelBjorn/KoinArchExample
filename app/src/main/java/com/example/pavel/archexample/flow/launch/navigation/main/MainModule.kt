package com.example.pavel.archexample.flow.launch.navigation.main

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import org.koin.dsl.context.ModuleDefinition

val mainModule: ModuleDefinition.() -> Unit = {
    module("Main") {
        scope("Main") { (activity: AppCompatActivity, fragmentManager: FragmentManager) ->
            MainPresenter(Navigator(activity, fragmentManager))
        }
    }
}