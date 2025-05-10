package com.example.github_profile.domain

import com.example.github_profile.data.GHRepoRepository
import com.example.github_profile.data.model.GHRepo

// create a Use Case class that calls the repository to get the GitHub repositories.

class FetchGHReposUseCase(private val repository: GHRepoRepository) {

    suspend fun getRepositories(username: String): List<GHRepo> {
        return repository.getRepositories(username)
    }

    suspend fun searchRepositories(query: String): List<GHRepo> {
        return repository.searchRepos(query)
    }
}
