package com.example.pavel.archexample.service.getaway

import io.reactivex.Single
import retrofit2.http.GET

interface SomeDataApi {
    @GET("url")
    fun getSomeData(): Single<List<SomeData>>
}


class SomeDataService(val apiExecutor: ApiExecutor) {

    fun getSomeData(): Single<List<SomeData>> {
        return apiExecutor.execute(SomeDataApi::class.java) { api -> api.getSomeData() }
    }
}

data class SomeData(val someProperty: Any)