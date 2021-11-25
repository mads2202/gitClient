package com.mads2202.gitclient.domen.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitUser(
    @PrimaryKey
     val id: String,
     val login: String? = null,
     val avatarUrl: String? = null,
     val reposUrl:String?=null
)