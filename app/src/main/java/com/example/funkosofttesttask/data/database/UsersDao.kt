package com.example.funkosofttesttask.data.database

import androidx.room.*
import com.example.funkosofttesttask.entity.User

@Dao
interface UsersDao {
    @Query("SELECT * FROM User")
    suspend fun getListReminders(): List<User>

    @Query("SELECT * FROM User WHERE id == :id")
    fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

}