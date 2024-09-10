package com.example.github.domain.action

import com.example.github.domain.model.Repo
import com.example.github.domain.repository.RepoRepository

class GetRepo(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(user: String): List<Repo> {
        return repoRepository.getRepos(user)
    }
}