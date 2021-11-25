package com.mads2202.gitclient.presenters

import com.mads2202.gitclient.domen.GitRepo
import com.mads2202.gitclient.network.GithubUsersRepo
import io.reactivex.Scheduler
import moxy.MvpPresenter

class ReposPresenter(val uiScheduler: Scheduler, val usersRepo: GithubUsersRepo, val url:String) : MvpPresenter<MainContact.MainView>() {
    var reposList= listOf<GitRepo>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    fun loadData() {
        usersRepo.getRepositories(url)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                reposList=repos
                viewState.updateList()
            }, {
                println("Error: ${it.stackTrace.toString()}")
            })
    }
}