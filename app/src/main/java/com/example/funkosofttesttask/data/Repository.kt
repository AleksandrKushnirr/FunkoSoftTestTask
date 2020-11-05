package com.example.funkosofttesttask.data

import com.example.funkosofttesttask.App
import com.example.funkosofttesttask.entity.User

class Repository {

    private val dao = App.instance.database.userDao()
    private val apiService = App.instance.usersApiService

    suspend fun getUsersList(): List<User>{
        val usersList = dao.getListReminders() as MutableList
        return if (usersList.isNotEmpty()){
            usersList
        }else{
            for (item in apiService.getUsersJson().body()?.data!!){
                with(User(item)) {
                    usersList.add(this)
                    dao.insert(this)
                }
            }
            usersList
        }
    }


    suspend fun getUserInfo(id: Int): User{
        return dao.getUserById(id) ?: User(apiService.getUserInfo(id).body()?.data!!)
    }

}