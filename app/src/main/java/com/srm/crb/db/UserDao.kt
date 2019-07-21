package com.srm.crb.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUserDetails(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE user_name LIKE :userName")
    fun getUser(userName: String): List<User>

    @Insert
    fun addUser(vararg users: User)

    @Delete
    fun remoeUser(user: User)
}