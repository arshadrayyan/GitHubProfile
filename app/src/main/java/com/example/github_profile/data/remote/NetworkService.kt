package com.example.github_profile.data.remote

import com.example.github_profile.data.model.GHRepo
import com.example.github_profile.data.model.RepoModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Network Service for API Request

class NetworkService {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/") // Base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubApiService: GitHubApiService = retrofit.create(GitHubApiService::class.java)

    suspend fun fetchRepositories(username: String): List<RepoModel> {
        val response = githubApiService.getRepositories(username)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("API Error: ${response.code()} ${response.message()}")
        }
    }
}