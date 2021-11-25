package com.mads2202.gitclient.presenters

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


class MainContact {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface MainView : MvpView {
        fun init()
        fun updateList()

    }

    abstract class LMainPresenter : MvpPresenter<MainView>() {

        abstract fun validateEmail(email: String)

        abstract fun validatePassword(password: String)

        abstract fun onLogin(email: String, password: String)

        abstract fun onForgetPassword()

        abstract fun onSingUp()
    }
}