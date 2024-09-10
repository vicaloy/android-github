package com.example.github.ui.repos

import androidx.compose.runtime.Stable
import com.example.github.domain.model.Repo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class ReposUiState(
    val isLoading: Boolean = false,
    val repos: ImmutableList<Repo> = persistentListOf(),
    val hasError: Boolean = false
)