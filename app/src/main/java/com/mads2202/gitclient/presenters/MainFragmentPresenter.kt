package com.mads2202.gitclient.presenters

import com.github.terrakok.cicerone.Router
import com.mads2202.gitclient.domen.GitUser
import com.mads2202.gitclient.network.GithubUsersRepo
import com.mads2202.gitclient.ui.Screens
import io.reactivex.Scheduler
import moxy.MvpPresenter

class MainFragmentPresenter(val uiScheduler: Scheduler, val usersRepo: GithubUsersRepo, val router: Router, val screens: Screens) : MvpPresenter<MainContact.MainView>() {
    var usersList= listOf<GitUser>()

        override fun onFirstViewAttach() {
            super.onFirstViewAttach()
            viewState.init()
            loadData()
        }

        fun loadData() {
            usersRepo.getUsers()
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    usersList=repos
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
        }
    }