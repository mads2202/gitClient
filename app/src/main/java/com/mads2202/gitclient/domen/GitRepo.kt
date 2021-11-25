package com.mads2202.gitclient.domen

import com.google.gson.annotations.Expose

data class GitRepo(
    @Expose var id: Int? = null,
    @Expose var name: String? = null,
    @Expose var description: String? = null,
)