package com.shefali.tahalufassessment

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UniversityViewModel(application: Application) : AndroidViewModel(application) {

    private val universityDao = AppDatabase.getDatabase(application).universityDao()
    private val repository = UniversityRepository(universityDao, RetrofitClient.instance)

    private val _universities = MutableLiveData<List<University>>()
    val universities: LiveData<List<University>> = _universities
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error
    fun fetchUniversities(context:Context) {
        if (isNetworkAvailable(context)) {

            viewModelScope.launch {
                try {
                    _universities.value = repository.fetchUniversities(context)
                } catch (e: Exception) {
                    _universities.value = emptyList()
                    _error.value = "Error fetching universities: ${e.message}"

                }
            }
        }else{
            viewModelScope.launch {
                try {
                    _universities.value = repository.getUniversitiesFromDb()
                } catch (e: Exception) {
                    _universities.value = emptyList()
                    _error.value = "Error fetching universities from local database: ${e.message}"
                }
            }
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show()
        }
    }
}
