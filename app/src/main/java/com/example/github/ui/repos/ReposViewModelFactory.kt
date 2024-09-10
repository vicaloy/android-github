package com.example.github.ui.repos

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.github.domain.action.GetRepo

class ReposViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val getRepo: GetRepo
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReposViewModel(getRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}