package com.example.github.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.model.Repo
import com.example.github.infra.services.RepoService
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReposViewModel(
    private val repoService: RepoService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReposUiState())
    val uiState = _uiState.asStateFlow()

    fun getRepos(user: String) {
        viewModelScope.launch(dispatcher) {
            try {
                setLoadingState()
                val repos = repoService(user).toImmutableList()
                setReposState(repos)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                setErrorState()
            }
        }
    }

    private fun setErrorState() {
        _uiState.update {
            it.copy(hasError = true, isLoading = false)
        }
    }

    private fun setReposState(repos: ImmutableList<Repo>) {
        _uiState.update {
            it.copy(
                hasError = false,
                isLoading = false,
                repos = repos
            )
        }
    }

    private fun setLoadingState() {
        _uiState.update {
            it.copy(
                hasError = false,
                isLoading = true,
                repos = persistentListOf()
            )
        }
    }
}