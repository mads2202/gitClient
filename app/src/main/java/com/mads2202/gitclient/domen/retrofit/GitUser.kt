package com.mads2202.gitclient.domen.retrofit

import com.google.gson.annotations.Expose

data class GitUser(
    @Expose val id: String,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl:String?=null
)
