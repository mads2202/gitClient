package com.mads2202.gitclient.network

import com.mads2202.gitclient.domen.retrofit.GitRepo
import com.mads2202.gitclient.domen.retrofit.GitUser
import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Url

interface DataSource {
    @GET("/users")
    fun getUsers(): Single<List<GitUser>>
    @GET
    fun getRepositories(@Url reposUrl:String):Single<List<GitRepo>>
}
