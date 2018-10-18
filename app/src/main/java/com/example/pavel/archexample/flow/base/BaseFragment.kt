package com.example.pavel.archexample.flow.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.core.scope.Scope
import java.lang.IllegalArgumentException

abstract class BaseFragment<V, P> : Fragment(), Contract.Controller<V, P> where V : Contract.BaseView, P : Contract.Presenter<V> {

    override var view: V? = null

    override var scope: Scope? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContenrViewLayoutRes(), container, false)
    }

    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)
        scope = getOrCreateScope(scopeName())
        @Suppress("UNCHECKED_CAST")
        when (rootView) {
            is Contract.BaseView -> view = rootView as V
            else -> throw IllegalArgumentException("View Should implement ${Contract.BaseView::class.java.name}")
        }
    }

    @LayoutRes
    protected abstract fun getContenrViewLayoutRes(): Int

    override fun onStart() {
        super.onStart()
        view?.run { presenter.attachView(this) }
    }

    override fun onStop() {
        view?.run { presenter.detachView() }
        super.onStop()
    }

    override fun onDestroy() {
        scope?.close()
        scope = null
        super.onDestroy()
    }
}
