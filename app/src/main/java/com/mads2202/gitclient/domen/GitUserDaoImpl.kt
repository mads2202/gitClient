package com.mads2202.gitclient.domen

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class GitUserDaoImpl:GitUserDao {
    private val cache= mutableListOf(GitUser("user1@gmail.ru","user1","user1"))
    private val source = BehaviorSubject.createDefault<List<GitUser>>(cache)
    override fun getUsers():Observable<List<GitUser>> =source

    override fun addUser(user: GitUser) {
        cache.add(user)
        source.onNext(cache)
    }

}