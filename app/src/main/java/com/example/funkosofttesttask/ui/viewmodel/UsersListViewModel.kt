package com.example.funkosofttesttask.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funkosofttesttask.data.Repository
import com.example.funkosofttesttask.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersListViewModel : ViewModel() {

    private val repository = Repository()

    private var mUserLiveData: MutableLiveData<User> = MutableLiveData()
    private var mUsersListLiveData: MutableLiveData<List<User>> = MutableLiveData()

    val userLiveData: LiveData<User> = mUserLiveData
    val usersListLiveData: LiveData<List<User>> = mUsersListLiveData

    init {
        viewModelScope.launch {
            mUsersListLiveData.value =
                withContext(Dispatchers.IO) { repository.getUsersList() }
        }
    }

    fun onUserSelect(id: Int) {
        viewModelScope.launch {
            mUserLiveData.value =
                withContext(Dispatchers.IO) { repository.getUserInfo(id) }
        }
    }
}