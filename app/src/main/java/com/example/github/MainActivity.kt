package com.example.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.example.github.di.Di
import com.example.github.ui.repos.RepoScreen
import com.example.github.ui.repos.ReposViewModel
import com.example.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ReposViewModel>(
        factoryProducer = {
            Di.provideReposViewModelFactory(
                owner = this,
                context = applicationContext
            )
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RepoScreen(
                        reposViewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubTheme {
        Greeting("Android")
    }
}