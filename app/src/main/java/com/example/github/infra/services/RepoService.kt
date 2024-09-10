package com.example.github.infra.services

import android.content.Context
import com.example.github.domain.action.GetRepo
import com.example.github.domain.action.SaveRepo
import com.example.github.domain.model.Repo
import com.example.github.network.isNetworkAvailable

class RepoService(
    private val context: Context,
    private val getRemoteRepo: GetRepo,
    private val getLocalRepo: GetRepo,
    private val saveRepo: SaveRepo
) {
    suspend operator fun invoke(user: String): List<Repo> {
        return if (isNetworkAvailable(context))
            getRemoteRepo(user).also { saveRepo(it) }
        else
            getLocalRepo(user)
    }
}