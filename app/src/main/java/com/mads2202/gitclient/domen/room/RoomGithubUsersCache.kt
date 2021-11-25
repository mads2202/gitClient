package com.mads2202.gitclient.domen.room

import com.mads2202.gitclient.domen.retrofit.GitUser
import io.reactivex.Single

class RoomGithubUsersCache(val db: Database) {
     fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GitUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }
}
