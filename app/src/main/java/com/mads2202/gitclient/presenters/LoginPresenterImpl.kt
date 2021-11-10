package com.mads2202.gitclient.presenters

import com.github.terrakok.cicerone.Router
import com.mads2202.gitclient.domen.GitUser
import com.mads2202.gitclient.domen.GitUserDao
import com.mads2202.gitclient.presenters.LoginContract.*
import com.mads2202.gitclient.ui.Screens
import com.mads2202.gitclient.ui.ViewState
import com.mads2202.gitclient.util.regExMailPattern
import com.mads2202.gitclient.util.regExPasswordPattern
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginPresenterImpl(val router: Router) : LoginPresenter() {

    override fun validateEmail(email: String) {
        if (!isValidString(Pattern.compile(regExMailPattern), email)) {
            viewState.setState(ViewState.MAIL_ERROR)
        }
    }

    override fun validatePassword(password: String) {
        if (!isValidString(Pattern.compile(regExPasswordPattern), password)) {
            viewState.setState(ViewState.PASSWORD_ERROR)
        }
    }

    override fun onLogin(email: String, password: String) {
        if (isValidCredentials(email, password)) {
            viewState.rememberCredentials(email,password)
            router.navigateTo(Screens.openMainScreen())
            viewState.setState(ViewState.SUCCESS)
        }
        viewState.setState(ViewState.ERROR)
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
        router.navigateTo(Screens.openForgotPasswordScreen())
    }

    override fun onSingUp() {
        router.navigateTo(Screens.openForgotPasswordScreen())
    }

    private fun isValidString(pattern: Pattern, str: String): Boolean {
        val matcher: Matcher = pattern.matcher(str)
        return matcher.find()
    }
}