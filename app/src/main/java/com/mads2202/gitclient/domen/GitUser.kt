package com.mads2202.gitclient.domen

import com.google.gson.annotations.Expose

data class GitUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl:String?=null
)
