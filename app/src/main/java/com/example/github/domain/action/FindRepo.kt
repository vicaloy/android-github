package com.example.github.domain.action

import com.example.github.domain.model.Repo
import com.example.github.domain.repository.RepoRepository

class FindRepo(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(user: String, repo: String): Repo {
        return repoRepository.findRepo(user, repo)
    }
}