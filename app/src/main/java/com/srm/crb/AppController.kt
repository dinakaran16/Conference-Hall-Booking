package com.srm.crb

import android.app.Application
import androidx.room.Room
import com.srm.crb.data.LoginDataSource
import com.srm.crb.data.LoginRepository
import com.srm.crb.db.AppDatabase

class AppController : Application() {
    lateinit var loginRepository: LoginRepository
    override fun onCreate() {
        super.onCreate()
        instance = this

        val roomDatabase = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        loginRepository = LoginRepository(LoginDataSource(roomDatabase))

    }

    companion object {
        lateinit var instance: AppController
    }

}