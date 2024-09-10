package com.example.github.infra.client

import com.example.github.infra.dto.RepoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoClient {
    @GET("/users/{user}/repos")
    suspend fun getRepos(
        @Path("user") user: String,
    ): List<RepoDTO>

    @GET("/users/{user}/{repo}")
    suspend fun findRepo(
        @Path("user") user: String,
        @Path("repo") repo: String,
    ): RepoDTO
}