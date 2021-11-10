package com.mads2202.gitclient.presenters

import com.mads2202.gitclient.ui.ViewState
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

class LoginContract {
    interface View : MvpView {
        @AddToEndSingle
        fun setState(state: ViewState)

        @Skip
        fun rememberCredentials(email: String, password: String)

    }

    abstract class LoginPresenter : MvpPresenter<View>() {

        abstract fun validateEmail(email: String)

        abstract fun validatePassword(password: String)

        abstract fun onLogin(email: String, password: String)

        abstract fun onForgetPassword()

        abstract fun onSingUp()
    }
}