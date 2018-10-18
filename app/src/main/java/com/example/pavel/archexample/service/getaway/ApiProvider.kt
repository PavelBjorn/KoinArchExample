package com.example.pavel.archexample.service.getaway

import com.example.pavel.archexample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface ApiProvider {
    fun <Api> provide(apiClazz: Class<Api>): Api
}

class RetrofitApiProvider(baseUrl: String) : ApiProvider {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .build()
    }

    private val cashedApi: MutableMap<String, Any> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    override fun <Api> provide(apiClazz: Class<Api>): Api {
        return cashedApi.getOrPut(apiClazz.name) {
            return@getOrPut retrofit.create(apiClazz::class.java)
        } as Api
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT_SECCONDS, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(
                        if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                ))
                .build()
    }

    companion object {
        const val READ_TIMEOUT_SECONDS = 60L
        const val CONNECTION_TIMEOUT_SECCONDS = 60L
        const val WRITE_TIMEOUT_SECONDS = 60L
    }
}


