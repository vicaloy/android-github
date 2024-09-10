package com.example.github.ui.repos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.github.domain.model.Repo
import kotlinx.collections.immutable.ImmutableList

@Composable
fun RepoScreen(reposViewModel: ReposViewModel, modifier: Modifier) {
    val uiState = reposViewModel.uiState.collectAsStateWithLifecycle()

    ReposList(repos = uiState.value.repos, onSearch = { reposViewModel.getRepos(it) }, modifier = modifier)
    Progress(isLoading = uiState.value.isLoading)
}

@Composable
private fun Progress(isLoading: Boolean) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}

@Composable
private fun ReposList(repos: ImmutableList<Repo>, onSearch: (String) -> Unit, modifier: Modifier) {
    var user by remember { mutableStateOf("") }
    Column(modifier = modifier.then(Modifier.padding(16.dp))) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            OutlinedTextField(value = user, onValueChange = { user = it })
            Button(onClick = { onSearch(user) }) {
                Text(text = "Search")
            }
        }

        LazyColumn {
            items(count = repos.size) { index ->
                Text(text = repos[index].name, modifier = Modifier.padding(16.dp))
            }
        }
    }
}