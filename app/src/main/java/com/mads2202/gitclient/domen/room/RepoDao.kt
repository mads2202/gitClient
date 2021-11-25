package com.mads2202.gitclient.domen.room

import androidx.room.*

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGitRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitRepo>)

    @Update
    fun update(user: RoomGitRepo)

    @Update
    fun update(vararg users: RoomGitRepo)

    @Update
    fun update(users: List<RoomGitRepo>)

    @Delete
    fun delete(user: RoomGitRepo)

    @Delete
    fun delete(vararg users: RoomGitRepo)

    @Delete
    fun delete(users: List<RoomGitRepo>)

    @Query("SELECT * FROM RoomGitRepo")
    fun getAll(): List<RoomGitRepo>

    @Query("SELECT * FROM RoomGitRepo WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGitRepo>
}
