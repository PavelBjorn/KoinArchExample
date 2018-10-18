package com.example.pavel.archexample.flow.launch

import com.example.pavel.archexample.flow.base.BasePresenter
import com.example.pavel.archexample.flow.base.Contract
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashPresenter : BasePresenter<SplashPresenter.SplashView>() {

    override fun onViewAttached() {
        Completable.fromAction { Thread.sleep(2000) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { ifViewAttached { it.closeSplash() } }
    }

    interface SplashView : Contract.BaseView {
        fun closeSplash()
    }
}