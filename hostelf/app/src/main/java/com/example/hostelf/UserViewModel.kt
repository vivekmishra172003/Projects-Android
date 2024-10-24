package com.example.hostelf

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val availableRoomsLiveData: MutableLiveData<Int> = MutableLiveData(100)

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        repository.getBookedRoomsCount().observeForever { bookedRooms ->
            availableRoomsLiveData.value = 100 - bookedRooms
        }
    }


    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }


    fun login(mobile: String, password: String): LiveData<User?> {
        val loginResult: MutableLiveData<User?> = MutableLiveData()
        viewModelScope.launch {
            val user: User? = repository.login(mobile, password)
            loginResult.postValue(user)
        }
        return loginResult
    }


    fun getAvailableRooms(): LiveData<Int> {
        return availableRoomsLiveData
    }
}
