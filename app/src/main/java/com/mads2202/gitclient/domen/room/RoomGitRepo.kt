package com.mads2202.gitclient.domen.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGitRepo(
    @PrimaryKey
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var forksCount: Int,
    var userId: String
)
