package com.mads2202.gitclient.network

import com.mads2202.gitclient.domen.retrofit.GitRepo
import com.mads2202.gitclient.domen.retrofit.GitUser
import com.mads2202.gitclient.domen.room.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GitHubUsersRepoImpl(val api: DataSource, val networkStatus: NetworkStatus, val db: Database) :
    GithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGitUser(
                                user.id ?: "",
                                user.login ?: "",
                                user.avatarUrl ?: "",
                                user.reposUrl ?: ""
                            )
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            RoomGithubUsersCache(db).getUsers()
        }
    }.subscribeOn(Schedulers.io())


    override fun getRepositories(user: GitUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                                    ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGitRepo(
                                        it.id ?: null,
                                        it.name ?: "",
                                        it.description ?: "",
                                        it.forksCount ?: 0,
                                        roomUser.id
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<GitRepo>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                RoomGithubRepositoriesCache(db).getRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}