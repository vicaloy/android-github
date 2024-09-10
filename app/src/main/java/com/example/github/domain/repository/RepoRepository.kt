package com.example.github.domain.repository

import com.example.github.domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(user: String): List<Repo>

    suspend fun findRepo(user: String, repo: String): Repo
}