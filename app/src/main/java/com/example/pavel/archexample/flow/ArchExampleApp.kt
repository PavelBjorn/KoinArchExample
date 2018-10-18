package com.example.pavel.archexample.flow

import android.app.Application
import com.example.pavel.archexample.flow.base.di.flowModule
import com.example.pavel.archexample.flow.base.di.initializerModule
import com.example.pavel.archexample.flow.base.initializer.Initializer
import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin

class ArchExampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(initializerModule, flowModule))
        get<Set<Initializer>>().forEach { initializer -> initializer.invoke(this) }
    }
}