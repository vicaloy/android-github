package com.example.github.infra.repository

import com.example.github.domain.model.Repo
import com.example.github.domain.repository.RepoExtendedRepository
import com.example.github.infra.database.dao.OwnerDAO
import com.example.github.infra.database.dao.RepoDAO
import com.example.github.infra.database.entity.OwnerEntity
import com.example.github.infra.database.entity.RepoEntity

class RepoLocalRepository(private val repoDAO: RepoDAO, private val ownerDAO: OwnerDAO) :
    RepoExtendedRepository {
    override suspend fun saveAll(repos: List<Repo>) {
        ownerDAO.insertAll(repos.map { it.owner }.map { OwnerEntity.fromModel(it) })
        repoDAO.insertAll(repos.map { RepoEntity.fromModel(it) })
    }

    override suspend fun getRepos(user: String): List<Repo> {
        return repoDAO.getAllByOwner(user).map { it.toModel() }
    }

    override suspend fun findRepo(user: String, repo: String): Repo {
        return repoDAO.find(user, repo).toModel()
    }
}