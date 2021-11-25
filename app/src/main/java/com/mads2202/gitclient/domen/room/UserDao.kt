package com.mads2202.gitclient.domen.room

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGitUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitUser>)

    @Update
    fun update(user: RoomGitUser)

    @Update
    fun update(vararg users: RoomGitUser)

    @Update
    fun update(users: List<RoomGitUser>)

    @Delete
    fun delete(user: RoomGitUser)

    @Delete
    fun delete(vararg users: RoomGitUser)

    @Delete
    fun delete(users: List<RoomGitUser>)

    @Query("SELECT * FROM RoomGitUser")
    fun getAll(): List<RoomGitUser>

    @Query("SELECT * FROM RoomGitUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGitUser?
}

