package com.example.pavel.archexample.flow.base.di

import com.example.pavel.archexample.flow.base.initializer.FabricInitializer
import com.example.pavel.archexample.flow.base.initializer.LickCanaryInitializer
import com.example.pavel.archexample.flow.base.initializer.StrictModeInitializer
import org.koin.dsl.module.module

val initializerModule = module {
    single {
        setOf(
                FabricInitializer,
                LickCanaryInitializer,
                StrictModeInitializer
        )
    }
}