package com.mads2202.gitclient.domen.retrofit

import com.google.gson.annotations.Expose

data class GitRepo(
    @Expose var id: Int? = null,
    @Expose var name: String? = null,
    @Expose var description: String? = null,
    @Expose var forksCount: Int? = null,
)