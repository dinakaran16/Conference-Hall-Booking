package com.srm.crb.data

import androidx.lifecycle.LiveData
import com.srm.crb.data.model.LoggedInUser
import com.srm.crb.db.AppDatabase
import com.srm.crb.db.User
import java.io.IOException
import java.lang.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val database: AppDatabase) {

    fun login(username: String, password: String): Result<LoggedInUser> {
        return if (isAdmin(username, password)) {
            Result.Success(LoggedInUser("10", "Admin"))
        } else {
            val resultArray = database.userDao().getUser(username)
            if (resultArray.isNotEmpty()) {
                val user = resultArray.first()
                if (user.password == password) {
                    Result.Success(LoggedInUser(user.uid, user.userName))
                } else {
                    Result.Error(IOException("Record not found"))
                }
            } else {
                Result.Error(IOException("Record not found"))
            }
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun isAdmin(username: String, password: String): Boolean {
        return "admin" == username && "admin" == password
    }

    fun getAllUsers(): LiveData<List<User>> {
        return database.userDao().getAllUserDetails()
    }

    fun removeUser(user: User) {
        database.userDao().remoeUser(user)
    }

    fun addUser(user: User) {
        database.userDao().addUser(user)
    }

    fun resetPassword(user: User, pass: String) {
        val newUser = User(user.uid, user.userName, pass, false)
        database.userDao().updateUser(newUser)
    }

    fun getUser(userName: String): User? {
        val resultArray = database.userDao().getUser(userName)
        if (resultArray.isNotEmpty()) {
            return resultArray.first()
        }
        return null
    }
}

