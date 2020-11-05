package com.example.funkosofttesttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.funkosofttesttask.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UsersDao
}
