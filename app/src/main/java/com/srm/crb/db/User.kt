package com.srm.crb.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey val uid: String,
        @ColumnInfo(name = "user_name") val userName: String,
        @ColumnInfo(name = "password") val password: String,
        @ColumnInfo(name = "should_reset_password") val shouldResetPassword: Boolean
)
