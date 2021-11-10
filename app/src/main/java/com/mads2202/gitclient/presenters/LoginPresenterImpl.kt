package com.mads2202.gitclient.presenters

import com.mads2202.gitclient.domen.GitUser
import com.mads2202.gitclient.domen.GitUserDao
import com.mads2202.gitclient.presenters.LoginContract.*
import com.mads2202.gitclient.ui.ViewState
import com.mads2202.gitclient.util.regExMailPattern
import com.mads2202.gitclient.util.regExPasswordPattern
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginPresenterImpl : LoginPresenter {
    var view: View? = null
    override fun onAttach(view: View) {
        this.view = view
    }

    override fun validateEmail(email: String) {
        if (!isValidString(Pattern.compile(regExMailPattern), email)) {
            view?.setState(ViewState.MAIL_ERROR)
        }
    }

    override fun validatePassword(password: String) {
        if (!isValidString(Pattern.compile(regExPasswordPattern), password)) {
            view?.setState(ViewState.PASSWORD_ERROR)
        }
    }

    override fun onDetach() {
        view = null
    }

    override fun onLogin(email: String, password: String) {
        if (isValidCredentials(email, password)) {
            view?.rememberCredentials(email,password)
            view?.openMainScreen()
            view?.setState(ViewState.SUCCESS)
        }
        view?.setState(ViewState.ERROR)
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        var user: GitUser? = null
        GitUserDao.gitUserList.forEach {
            if (it.email == email) {
                user = it
                return@forEach
            }
        }
        return user?.password == password
    }

    override fun onForgetPassword() {
        view?.openForgotPasswordScreen()
    }

    override fun onSingUp() {
        view?.openSingUpScreen()
    }

    private fun isValidString(pattern: Pattern, str: String): Boolean {
        val matcher: Matcher = pattern.matcher(str)
        return matcher.find()
    }
}