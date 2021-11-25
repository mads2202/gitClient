package com.mads2202.gitclient.domen.room

import com.mads2202.gitclient.domen.retrofit.GitRepo
import com.mads2202.gitclient.domen.retrofit.GitUser
import io.reactivex.Single

class RoomGithubRepositoriesCache(val db: Database) {
    fun getRepositories(user: GitUser) = Single.fromCallable {
        val roomUser = user.login?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        db.repositoryDao.findForUser(roomUser.id)
            .map { GitRepo(it.id, it.name, it.forksCount.toString()) }
    }
}