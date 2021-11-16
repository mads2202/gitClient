package com.mads2202.gitclient.presenters

import com.github.terrakok.cicerone.Router
import com.mads2202.gitclient.domen.GitUser
import com.mads2202.gitclient.domen.GitUserDao
import com.mads2202.gitclient.domen.GitUserDaoImpl
import com.mads2202.gitclient.presenters.LoginContract.*
import com.mads2202.gitclient.ui.Screens
import com.mads2202.gitclient.ui.ViewState
import com.mads2202.gitclient.util.regExMailPattern
import com.mads2202.gitclient.util.regExPasswordPattern
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginPresenterImpl(val router: Router) : LoginPresenter() {
    lateinit var compositeDisposable: CompositeDisposable
    private val userRepo: GitUserDao = GitUserDaoImpl()
    override fun onFirstViewAttach() {
        compositeDisposable = CompositeDisposable()
        super.onFirstViewAttach()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

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
            viewState.rememberCredentials(email, password)
            router.navigateTo(Screens.openMainScreen())
            viewState.setState(ViewState.SUCCESS)
        }
        viewState.setState(ViewState.ERROR)
    }

    private fun isValidCredentials(email: String, password: String): Boolean {

        var isValid = false
        compositeDisposable.add(
            userRepo.getUsers()
                .flatMap { users -> Observable.fromIterable(users) }
                .filter { user -> user.email == email }
                .subscribe { user ->
                    isValid = user.password == password
                })
        return isValid
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