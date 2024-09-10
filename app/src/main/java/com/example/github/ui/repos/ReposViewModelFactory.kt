package com.example.github.ui.repos

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.github.domain.action.GetRepo
import com.example.github.domain.action.SaveRepo
import com.example.github.infra.services.RepoService

class ReposViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repoService: RepoService
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReposViewModel(repoService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}