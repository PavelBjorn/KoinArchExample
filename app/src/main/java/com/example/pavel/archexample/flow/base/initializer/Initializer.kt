package com.example.pavel.archexample.flow.base.initializer

import android.app.Application
import android.os.StrictMode
import android.util.Log

typealias Initializer = (Application) -> Unit

val FabricInitializer: Initializer = {
    Log.d("Initializer", "FabricInitializer ")
}

val LickCanaryInitializer: Initializer = {
    Log.d("Initializer", "LickCanaryInitializer ")
}

val StrictModeInitializer: Initializer = {
    StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
    )
    StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyFlashScreen()
                    .build()
    )
}