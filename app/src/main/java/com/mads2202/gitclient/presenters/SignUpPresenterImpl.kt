package com.mads2202.gitclient.presenters

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
import io.reactivex.disposables.CompositeDisposable
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

    private fun isNewMail(mail: String): Boolean {
        var isNew = true
        compositeDisposable.add(
            userRepo.getUsers()
                .flatMap { users -> Observable.fromIterable(users) }
                .skipWhile { user -> user.email != mail }
                .subscribe { user ->
                    isNew = false
                })
        return isNew

    }

    private fun isNewNickname(nickName: String): Boolean {
        var isNew = true
        compositeDisposable.add(
            userRepo.getUsers()
                .flatMap { users -> Observable.fromIterable(users) }
                .skipWhile { user -> user.nickname != nickName }
                .subscribe { user ->
                    isNew = false
                })
        return isNew

    }

    override fun onSingUp(mail: String, password: String, nickName: String) {
        if (isNewMail(mail)) {
            if (isNewNickname(nickName)) {
                userRepo.addUser(GitUser(mail, password, nickName))
                router.navigateTo(Screens.openLoginScreen())
            } else {
                viewState.setState(ViewState.NICKNAME_ERROR)
            }
        } else {
            viewState.setState(ViewState.MAIL_ERROR)
        }

    }
}