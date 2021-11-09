package com.mads2202.gitclient.presenters

import com.mads2202.gitclient.ui.ViewState

class LoginContract {
    interface View {

        fun setState(state: ViewState)

        fun openSingUpScreen()

        fun openForgotPasswordScreen()

        fun openMainScreen()

    }

    interface LoginPresenter {

        fun onAttach(view: View)

        fun validateEmail(email: String)

        fun validatePassword(password: String)

        fun onDetach()

        fun onLogin(email: String, password: String)

        fun onForgetPassword()

        fun onSingUp()
    }
}