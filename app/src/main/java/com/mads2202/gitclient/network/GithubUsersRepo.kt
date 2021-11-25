package com.mads2202.gitclient.network

import com.mads2202.gitclient.domen.GitRepo
import com.mads2202.gitclient.domen.GitUser
import io.reactivex.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GitUser>>
    fun getRepositories( reposUrl:String):Single<List<GitRepo>>

}