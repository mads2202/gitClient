package com.mads2202.gitclient.domen

import io.reactivex.Observable

interface GitUserDao {

    fun getUsers():Observable<List<GitUser>>

    fun addUser(user:GitUser)
}