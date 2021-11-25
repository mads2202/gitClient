package com.mads2202.gitclient.network

import com.mads2202.gitclient.domen.GitRepo
import com.mads2202.gitclient.domen.GitUser
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GitHubUsersRepoImpl(val api:DataSource):GithubUsersRepo {
    override fun getUsers(): Single<List<GitUser>> =api.getUsers().subscribeOn(Schedulers.io())
    override fun getRepositories(reposUrl: String): Single<List<GitRepo>> =api.getRepositories(reposUrl).subscribeOn(Schedulers.io())
}