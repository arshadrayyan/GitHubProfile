package com.example.github_profile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_profile.data.model.GHRepo
import com.example.github_profile.domain.FetchGHReposUseCase
import kotlinx.coroutines.launch

// ViewModel interacts with the UseCase and provides data to the UI using LiveData.

class GHRepoViewModel(private val useCase: FetchGHReposUseCase) : ViewModel() {

    private val _repos = MutableLiveData<List<GHRepo>>()
    val repos: LiveData<List<GHRepo>> = _repos

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchRepos(username: String) {
        viewModelScope.launch {
            try {
                val data = useCase.getRepositories(username)
                _repos.value = data
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun searchRepos(query: String) {
        viewModelScope.launch {
            try {
                val data = useCase.searchRepositories(query)
                _repos.value = data
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
