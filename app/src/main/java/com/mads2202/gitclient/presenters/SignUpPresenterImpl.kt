package com.mads2202.gitclient.presenters

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.mads2202.gitclient.domen.GitUser
import com.mads2202.gitclient.domen.GitUserDao
import com.mads2202.gitclient.domen.GitUserDaoImpl
import com.mads2202.gitclient.ui.Screens
import com.mads2202.gitclient.ui.ViewState
import com.mads2202.gitclient.util.isValidString
import com.mads2202.gitclient.util.regExMailPattern
import com.mads2202.gitclient.util.regExPasswordPattern
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class SignUpPresenterImpl(val router: Router) : SignUpContract.SignUpPresenter() {
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

    override fun onSingUp(mail: String, password: String, nickName: String) {
    }
}