package com.example.github.domain.action

import com.example.github.domain.model.Repo
import com.example.github.domain.repository.RepoExtendedRepository

class SaveRepo(private val repository: RepoExtendedRepository) {
    suspend operator fun invoke(repos: List<Repo>) {
        repository.saveAll(repos)
    }
}