package com.example.funkosofttesttask

import android.app.Application
import androidx.room.Room
import com.example.funkosofttesttask.data.database.AppDatabase
import com.example.funkosofttesttask.data.web.UsersApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object{
        lateinit var instance: App
        const val BASE_URL = "https://reqres.in/"
    }

    lateinit var usersApiService: UsersApiService
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
        initRoom()
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        usersApiService = retrofit.create(UsersApiService::class.java)
    }

    private fun initRoom(){
        database = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "users_database")
            .build()
    }
}