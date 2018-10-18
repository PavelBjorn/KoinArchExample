package com.example.pavel.archexample.flow.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.core.scope.Scope
import java.lang.IllegalArgumentException


abstract class BaseActivity<V, P> : AppCompatActivity(), Contract.Controller<V, P> where V : Contract.BaseView, P : Contract.Presenter<V> {

    override var view: V? = null

    override var scope: Scope? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewLayoutRes())
        view = getContentView()
        scope = getOrCreateScope(scopeName())
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        view?.run { presenter.attachView(this) }
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun onDestroy() {
        scope?.close()
        scope = null
        super.onDestroy()
    }

    @LayoutRes
    protected abstract fun getContentViewLayoutRes(): Int

    @Suppress("UNCHECKED_CAST")
    private fun getContentView(): V? {
        val contentView = (findViewById(android.R.id.content) as? ViewGroup)?.getChildAt(0)

        return when (contentView) {
            is Contract.BaseView -> contentView as V
            else -> throw IllegalArgumentException("View Should implement ${Contract.BaseView::class.java.name}")
        }
    }

    inline fun <D> LiveData<D>.observe(crossinline observer: (D) -> Unit) {
        observe(this@BaseActivity, Observer { data -> data?.let { observer.invoke(it) } })
    }

}