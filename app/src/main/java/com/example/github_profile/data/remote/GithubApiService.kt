package com.example.github_profile.data.remote

//GithubApiService

import com.example.github_profile.data.model.RepoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("users/{user}/repos")
    suspend fun getRepositories(
        @Path("user") user: String
    ): Response<List<RepoModel>>

    @GET("repos/{user}/{repo}")
    suspend fun getRepoDetails(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): Response<RepoModel>
}


