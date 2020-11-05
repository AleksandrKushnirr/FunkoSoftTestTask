package com.example.funkosofttesttask.entity

import com.example.funkosofttesttask.data.web.pojo.Data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var image: String = ""
){
    constructor(data: Data): this(){
        this.id = data.id
        this.name = data.first_name
        this.surname = data.last_name
        this.email = data.email
        this.image = data.avatar
    }
}