package com.example.pavel.archexample.flow.base

interface Contract {

    interface Controller<V, P> where V : BaseView, P : Presenter<V> {

        val presenter: P

        var view: V?
    }

    interface Presenter<V : BaseView> {

        fun attachView(view: V)

        fun detachView()
    }

    interface BaseView
}


abstract class BasePresenter<V> : Contract.Presenter<V> where V : Contract.BaseView {

    private var view: V? = null

    override fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    protected abstract fun onViewAttached()

    override fun detachView() {
        this.view = null
    }

    fun ifViewAttached(action: (V) -> Unit) = view?.run { action.invoke(this) }
}