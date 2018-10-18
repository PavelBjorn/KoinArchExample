package com.example.pavel.archexample.flow.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import java.lang.IllegalArgumentException

abstract class BaseFragment<V, P> : Fragment(), Contract.Controller<V, P> where V : Contract.BaseView, P : Contract.Presenter<V> {

    override var view: V? = null

    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        when (view) {
            is View -> view = rootView as V
            else -> throw IllegalArgumentException("View Should implement ${Contract.BaseView::class.java.name}")
        }
    }

    override fun onStart() {
        super.onStart()
        view?.run { presenter.attachView(this) }
    }

    override fun onDestroy() {
        view?.run { presenter.detachView() }
        super.onDestroy()
    }
}
