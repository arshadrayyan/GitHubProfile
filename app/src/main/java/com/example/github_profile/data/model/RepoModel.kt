package com.example.github_profile.data.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val language: String?,
    @SerializedName("html_url") val repoURL: String?,
    val htmlUrl: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val open_issues_count: Int,
    val watchers_count: Int,
    val owner: Owner
)

data class Owner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)