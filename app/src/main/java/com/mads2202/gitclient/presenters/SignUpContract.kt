package com.mads2202.gitclient.presenters

import com.mads2202.gitclient.ui.ViewState
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


class SignUpContract {
    interface SignUpView : MvpView {
        @AddToEndSingle
        fun setState(state: ViewState)

    }

    abstract class SignUpPresenter : MvpPresenter<SignUpView>() {

        abstract fun validateEmail(email: String)

        abstract fun validatePassword(password: String)

        abstract fun onSingUp(mail: String, password: String, nickName: String)

    }
}