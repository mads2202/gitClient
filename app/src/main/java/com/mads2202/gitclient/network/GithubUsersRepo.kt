package com.mads2202.gitclient.network

import com.mads2202.gitclient.domen.retrofit.GitRepo
import com.mads2202.gitclient.domen.retrofit.GitUser
import io.reactivex.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GitUser>>
    fun getRepositories( user: GitUser):Single<List<GitRepo>>

}