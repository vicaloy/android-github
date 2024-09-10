package com.example.github.infra.repository

import com.example.github.domain.repository.RepoRepository
import com.example.github.infra.client.RepoClient

class RepoRemoteRepository(private val repoClient: RepoClient): RepoRepository {

    override suspend fun getRepos(user: String) = repoClient.getRepos(user).map { it.mapToRepo() }

    override suspend fun findRepo(user: String, repo: String) = repoClient.findRepo(user, repo).mapToRepo()
}