package com.mads2202.gitclient.domen

interface GitUserDao {

    fun getUser():GitUser

    fun addUser(user:GitUser)
}